package com.chengtao.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.chengtao.entity.CompanyUser;
import com.chengtao.entity.MoblieResponse;
import com.chengtao.entity.NullObject;
import com.chengtao.entity.PersonUser;
import com.chengtao.entity.WebTip;
import com.chengtao.utils.MD5;
import com.chengtao.utils.ParamUtils;
import com.chengtao.utils.StringUtils;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class PersonUserServlet
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	// �ϴ�����
	private static final String AVATAR_PATH = "/upload/images";
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (initReqRes(request, response)) {
			response.setCharacterEncoding("UTF-8");
			if (request.getRequestURI().contains(ParamUtils.SERVLET_USER_LOGIN)) {// ��¼
				userLogin();
			} else if (request.getRequestURI().contains(ParamUtils.SERVLET_USER_LOGOUT)) {// ע��
				clearUserCookie();
				endTask(ParamUtils.VIEW_LOGIN);
			} else if (request.getRequestURI().contains(ParamUtils.SERVLET_USER_SIGNUP)) {// ע��
				userSignUp();
			} else if (request.getRequestURI().contains(ParamUtils.SERVLET_USER_INFO)) {//������Ϣ
				getCurrentUser();
				endTask(ParamUtils.VIEW_USR_INFO);
			}else if (request.getRequestURI().contains(ParamUtils.SERVLET_USER_MODIFY)) {//��Ϣ�޸�
				modifyUserInfo();
			}else if (request.getRequestURI().contains(ParamUtils.SERVLET_USER_AVATAR)) {//�ϴ�ͷ��
				uploadUserAvatar();
			}
			else {
				sendRedirect(ParamUtils.SERVLET_ACTIVITY);
			}
		}
	}
	/**
	 * �ϴ�ͼƬ
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void uploadUserAvatar() throws IOException, ServletException {
		int id = 0;
		int type = 0;
		String avatarPath = "";
		boolean isSuccess = true;
		Object object = getCurrentUser();
		File oldAvatar = null;
		String oldAvatarPath = null;
		if (object instanceof CompanyUser) {
			oldAvatarPath = ((CompanyUser)object).getAvatar();
		}else if(object instanceof PersonUser){
			oldAvatarPath = ((PersonUser)object).getAvatar();
		}
		if (oldAvatarPath != null && !oldAvatarPath.equals("")) {
			String fileName = oldAvatarPath.substring(oldAvatarPath.lastIndexOf(File.separator ));
			oldAvatar = new File(getServletContext().getRealPath("./") + File.separator  + AVATAR_PATH + fileName);
		}
		if (isPhone) {
			if (isSuccess) {
				if (oldAvatar != null) {
					oldAvatar.delete();
				}
			}
		}else {
			try {
				id = Integer.parseInt(mRequest.getParameter(ParamUtils.FOREM_USER_ID));
				type = Integer.parseInt(mRequest.getParameter(ParamUtils.FOREM_USER_TYPE));
			} catch (Exception e) {
				id = 0;
				type = 0;
			}
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(MEMORY_THRESHOLD);
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			ServletFileUpload upload = new ServletFileUpload(factory);
			String uploadPath = getServletContext().getRealPath("./") + File.separator  + AVATAR_PATH;
			File uploadDir = new File(AVATAR_PATH);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			try {
				List<FileItem> formItems = upload.parseRequest( mRequest);
				if (formItems != null && formItems.size() > 0) {
					for (FileItem item : formItems) {
						if (!item.isFormField()) {
							String fileName = new File(item.getName()).getName();
							String realPath =  System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
	                        String filePath = uploadPath + File.separator + realPath;
	                        File storeFile = new File(filePath);
	                        item.write(storeFile);
	                        avatarPath = mRequest.getContextPath() + AVATAR_PATH + File.separator + realPath;
						}
					}
				}
			} catch (Exception e) {
				isSuccess = false;
				setWebTip(new WebTip(false, "�ϴ�ͼƬʧ��", "�ϴ�ͼƬʧ��"));
				endTask(ParamUtils.VIEW_USR_INFO);
			}
			switch (type) {
			case PERSON_USER:
				if (personUserDao.uploadAvatar(id, avatarPath)) {
					setWebTip(new WebTip(false, "�ϴ�ͼƬ�ɹ�", "�ϴ�ͼƬ�ɹ�"));
				}else {
					setWebTip(new WebTip(false, "�ϴ�ͼƬʧ��", "�ϴ�ͼƬʧ��"));
					isSuccess = false;
				}
				break;
			case COMPANY_USER:
				if (companyUserDao.uploadAvatar(id, avatarPath)) {
					setWebTip(new WebTip(false, "�ϴ�ͼƬ�ɹ�", "�ϴ�ͼƬ�ɹ�"));
				}else {
					setWebTip(new WebTip(false, "�ϴ�ͼƬʧ��", "�ϴ�ͼƬʧ��"));
					isSuccess = false;
				}
				break;
			}
			if (isSuccess) {
				if (oldAvatar != null) {
					oldAvatar.delete();
				}
			}
			getCurrentUser();
			endTask(ParamUtils.VIEW_USR_INFO);
		}
	}
	/**
	 * �޸��û���Ϣ
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void modifyUserInfo() throws ServletException, IOException {
		PersonUser personUser = new PersonUser();
		CompanyUser companyUser = new CompanyUser();
		boolean isSuccess = true;
		Object currentUser = getCurrentUser();
		if (currentUser != null) {
			switch (initUser(personUser,companyUser)) {
			case PERSON_USER:
				if (StringUtils.isStrNotNull(personUser.getName())) {
					setUserCookies(personUser);
					if (!isPhone) {
						setUserAttr(personUser);
						setWebTip(new WebTip(true,"�޸���Ϣ�ɹ�","�޸���Ϣ�ɹ�"));
					}
				}
				break;
			case COMPANY_USER:
				if (StringUtils.isStrNotNull(companyUser.getName())){
					setUserCookies(companyUser);
					if (!isPhone) {
						setUserAttr(companyUser);
						setWebTip(new WebTip(true,"�޸���Ϣ�ɹ�","�޸���Ϣ�ɹ�"));
					}
				}
				break;
			default:
				isSuccess = false;
				if (!isPhone) {
					setUserAttr(getCurrentUser());
					setWebTip(new WebTip(true,"�޸���Ϣʧ��","�޸���Ϣʧ��"));
				}
				break;
			}
		}else {
			isSuccess = false;
			if (!isPhone) {
				setWebTip(new WebTip(true,"�޸���Ϣʧ��","�޸���Ϣʧ��"));
			}
		}
		if (isPhone) {
			if (isSuccess) {
				
			}else {
				
			}
		}else {
			endTask(ParamUtils.VIEW_USR_INFO);
		}
	}

	private int initUser(PersonUser personUser, CompanyUser companyUser) {
		int id = 0;
		int type = -1;
		String name = null;
		String passwword = null;
		String nickName = null;
		String companyName = null;
		String realName = null;
		String email = null;
		String qq = null;
		String tel = null;
		String address = null;
		String intro = null;
		if (isPhone) {
			
		}else {
			try {
				id = Integer.parseInt(mRequest.getParameter(ParamUtils.FOREM_USER_ID));
			} catch (Exception e) {
				id = 0;
			}
			try {
				type = Integer.parseInt(mRequest.getParameter(ParamUtils.FOREM_USER_TYPE));
			} catch (Exception e) {
				type = -1;
			}
			if (id != 0 && type != -1) {
				name = mRequest.getParameter(ParamUtils.FOREM_USER_NAME);
				passwword = mRequest.getParameter(ParamUtils.FOREM_USER_PASSWORD);
				passwword = MD5.getMD5Password(passwword);
				nickName = mRequest.getParameter(ParamUtils.FOREM_USER_NICK_NAME);
				companyName = mRequest.getParameter(ParamUtils.FOREM_USER_COMPANY_NAME);
				realName = mRequest.getParameter(ParamUtils.FOREM_USER_REAL_NAME);
				email = mRequest.getParameter(ParamUtils.FOREM_USER_EMAIL);
				qq = mRequest.getParameter(ParamUtils.FOREM_USER_QQ);
				tel = mRequest.getParameter(ParamUtils.FOREM_USER_TEL);
				address = mRequest.getParameter(ParamUtils.FOREM_USER_ADDRESS);
				intro = mRequest.getParameter(ParamUtils.FOREM_USER_INTRO);
				if (StringUtils.isStrNotNull(name,passwword)) {
					if (type == PERSON_USER) {
						PersonUser pUser = new PersonUser(id,name,passwword,nickName,realName,null,email,address,qq,tel,intro,type);
						personUser.copy(personUserDao.modify(pUser));
					}else {
						CompanyUser cUser = new CompanyUser(id,name,passwword,companyName,realName,null,email,address,qq,tel,intro,type);
						companyUser.copy(companyUserDao.modify(cUser));
					}
				}
			}
		}
		return type;
	}

	/**
	 * �û�ע��
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	private void userSignUp() throws IOException, ServletException {
		PersonUser personUser = null;
		CompanyUser companyUser = null;
		StringBuffer name = new StringBuffer();
		StringBuffer password = new StringBuffer();
		// ��ʼ���û���������
		boolean state = initNameAndPassword(name, password);
		Object object = signUp(name.toString(), password.toString(), state);
		handleSignUpOrLogin(personUser, companyUser, object, "ע��ɹ�", "ע��ʧ��", ParamUtils.VIEW_SIGN_UP);
	}

	/**
	 * �û���¼
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	private void userLogin() throws IOException, ServletException {
		PersonUser personUser = null;
		CompanyUser companyUser = null;
		StringBuffer name = new StringBuffer();
		StringBuffer password = new StringBuffer();
		// ��ʼ���û���������
		initNameAndPassword(name, password);
		// ��¼
		Object object = login(name.toString(), password.toString());
		handleSignUpOrLogin(personUser, companyUser, object, "��¼�ɹ�", "��¼ʧ��", ParamUtils.VIEW_LOGIN);
	}

	@SuppressWarnings("unused")
	/**
	 * ������¼ע��͵�¼
	 * 
	 * @param personUser
	 *            �����û�
	 * @param companyUser
	 *            ��ҵ�û�
	 * @param object
	 *            �û�����
	 * @param successMessage
	 *            �ɹ���Ϣ
	 * @param failMessage
	 *            ʧ����Ϣ
	 * @param path
	 *            �����ַ
	 * @throws IOException
	 * @throws ServletException
	 */
	private void handleSignUpOrLogin(PersonUser personUser, CompanyUser companyUser, Object object,
			String successMessage, String failMessage, String path) throws IOException, ServletException {
		if (object != null) {
			if (object instanceof PersonUser) {// �����û�
				personUser = (PersonUser) object;
				if (isPhone) {
					MoblieResponse<PersonUser, NullObject> moblieResponse = new MoblieResponse<>();
					if (personUser != null) {
						moblieResponse.setState(true);
						moblieResponse.setMessage(successMessage);
					} else {
						moblieResponse.setState(false);
						moblieResponse.setMessage(failMessage);
					}
					moblieResponse.setData(personUser);
					returnJson(moblieResponse);
				} else {
					if (personUser != null) {
						setWebTip(new WebTip(true, successMessage, successMessage + "��~~"));
					} else {
						setWebTip(new WebTip(false, failMessage, failMessage + ",�����԰�~~"));
					}
					endTask(path);
				}
			} else if (object instanceof CompanyUser) {// ��ҵ�û�
				companyUser = (CompanyUser) object;
				if (isPhone) {
					MoblieResponse<CompanyUser, NullObject> moblieResponse = new MoblieResponse<>();
					if (companyUser != null) {
						moblieResponse.setState(true);
						moblieResponse.setMessage(successMessage);
					} else {
						moblieResponse.setState(false);
						moblieResponse.setMessage(failMessage);
					}
					moblieResponse.setData(companyUser);
					returnJson(moblieResponse);
				} else {
					if (companyUser != null) {
						setWebTip(new WebTip(true, successMessage, successMessage + "��~~"));
					} else {
						setWebTip(new WebTip(false, failMessage, failMessage + ",�����԰�~~"));
					}
					endTask(path);
				}
			}
		} else {
			if (isPhone) {
				MoblieResponse<NullObject, NullObject> moblieResponse = new MoblieResponse<>();
				moblieResponse.setState(false);
				moblieResponse.setMessage(failMessage);
				returnJson(moblieResponse);
			} else {
				setWebTip(new WebTip(false, failMessage, failMessage + ",�����԰�~~"));
				endTask(path);
			}
		}
	}

	/**
	 * ��ʼ���û���������
	 * 
	 * @param name
	 * @param password
	 * @return �Ƿ���ҵ�û�
	 */
	private boolean initNameAndPassword(StringBuffer name, StringBuffer password) {
		// �ж��豸
		judgeDevice();
		String type = null;
		if (isPhone) {
			JsonObject object = handlePostJsonData();
			String n = object.get(ParamUtils.ATTR_USER_NAME).getAsString();
			name.append(n);
			String p = object.get(ParamUtils.ATTR_USER_PASSWORD).getAsString();
			password.append(p);
			try {
				type = object.get(ParamUtils.ATTR_USER_TYPE).getAsString();
			} catch (Exception e) {
				type = null;
			}
		} else {
			String n = mRequest.getParameter(ParamUtils.ATTR_USER_NAME);
			name.append(n);
			String p = mRequest.getParameter(ParamUtils.ATTR_USER_PASSWORD);
			p = MD5.getMD5Password(p);
			password.append(p);
			type = mRequest.getParameter(ParamUtils.ATTR_USER_TYPE);
		}
		if (!StringUtils.isStrNotNull(name.toString(), password.toString())) {
			name.append("");
			password.append("");
		}
		return type == null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}