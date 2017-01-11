package com.chengtao.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.chengtao.entity.Demand;
import com.chengtao.entity.Exhibition;
import com.chengtao.entity.ListInfo;
import com.chengtao.entity.MoblieResponse;
import com.chengtao.entity.News;
import com.chengtao.entity.NullObject;
import com.chengtao.entity.Supply;
import com.chengtao.entity.TabType;
import com.chengtao.entity.WebTip;
import com.chengtao.utils.ParamUtils;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class Info
 */
@WebServlet("/activity/*")
public class ActivityServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final int PAGE_SIZE = ParamUtils.PAGE_SIZE;

	/**
	 * @see BaseServlet#BaseServlet()
	 */
	public ActivityServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (initReqRes(request, response)) {
			String tab = null;
			int page = 1;
			if (getCurrentUser() != null) {
				ArrayList<News> news = null;
				ArrayList<Exhibition> exhibitions = null;
				ArrayList<Supply> supplies = null;
				ArrayList<Demand> demands = null;
				if (isPhone) {
					JsonObject object = handlePostJsonData();
					try {
						tab = object.get(ParamUtils.TAB).getAsString();
						page = object.get(ParamUtils.PAGE).getAsInt();
					} catch (Exception e) {
						
					}
				} else {
					tab = mRequest.getParameter(ParamUtils.TAB);
					try {
						page = Integer.parseInt(mRequest.getParameter(ParamUtils.PAGE));
					} catch (Exception e) {
						page = 1;
					}
				}
				if (tab == null) {
					tab = "";
				}
				switch (tab) {
				case ParamUtils.TAB_EXHIBITON:
					exhibitions = getExhibition(page);
					break;
				case ParamUtils.TAB_SUPPLY:
					supplies = getSupply(page);
					break;
				case ParamUtils.TAB_DEMAND:
					demands = getDemand(page);
					break;
				case ParamUtils.TAB_NEWS:
				default:
					news = getNews(page);
					break;
				}
				if (isPhone) {
					boolean isSuccess = true;
					switch (tab) {
					case ParamUtils.TAB_EXHIBITON:
						if (exhibitions == null) {
							isSuccess = false;
						}else {
							MoblieResponse<ListInfo, Exhibition> eResponse = new MoblieResponse<>();
							eResponse.setState(true);
							eResponse.setMessage("��ȡչ��ɹ�");
							eResponse.setData(new ListInfo(exhibitions.size(), exhibitions.get(0).getTotal()));
							eResponse.setDataList(exhibitions);
							returnJson(eResponse);
						}
						break;
					case ParamUtils.TAB_SUPPLY:
						if (supplies == null) {
							isSuccess = false;
						}else {
							MoblieResponse<ListInfo, Supply> sResponse = new MoblieResponse<>();
							sResponse.setState(true);
							sResponse.setMessage("��ȡ����ɹ�");
							sResponse.setData(new ListInfo(supplies.size(), supplies.get(0).getTotal()));
							sResponse.setDataList(supplies);
							returnJson(sResponse);
						}
						break;
					case ParamUtils.TAB_DEMAND:
						if (demands == null) {
							isSuccess = false;
						}else {
							MoblieResponse<ListInfo, Demand> dResponse = new MoblieResponse<>();
							dResponse.setState(true);
							dResponse.setMessage("��ȡ����ɹ�");
							dResponse.setData(new ListInfo(demands.size(), demands.get(0).getTotal()));
							dResponse.setDataList(demands);
							returnJson(dResponse);
						}
						break;
					case ParamUtils.TAB_NEWS:
						if (news == null) {
							isSuccess = false;
						}else {
							MoblieResponse<ListInfo,News> nResponse = new MoblieResponse<>();
							nResponse.setState(true);
							nResponse.setMessage("��ȡ��Ѷ�ɹ�");
							nResponse.setData(new ListInfo(news.size(), news.get(0).getTotal()));
							nResponse.setDataList(news);
							returnJson(nResponse);
						}
						break;
					default:
						isSuccess = false;
						break;
					}
					if (!isSuccess) {
						MoblieResponse<NullObject, NullObject> nnResponse = new MoblieResponse<>();
						nnResponse.setState(false);
						nnResponse.setMessage("��ȡ��Ϣʧ��");
						nnResponse.setDataList(null);
						returnJson(nnResponse);
					}
				} else {
					switch (tab) {
					case ParamUtils.TAB_EXHIBITON:
						deleteBefore(page,exhibitions);
						mRequest.setAttribute(ParamUtils.ENTITY_EXHIBITON, exhibitions);
						break;
					case ParamUtils.TAB_SUPPLY:
						deleteBefore(page,supplies);
						mRequest.setAttribute(ParamUtils.ENTITY_SUPPLY, supplies);
						break;
					case ParamUtils.TAB_DEMAND:
						deleteBefore(page,demands);
						mRequest.setAttribute(ParamUtils.ENTITY_DEMAND, demands);
						break;
					case ParamUtils.TAB_NEWS:
					default:
						deleteBefore(page,news);
						mRequest.setAttribute(ParamUtils.ENTITY_NEWS, news);
						break;
					}
					endTask(ParamUtils.VIEW_ACTIVITY);
				}
			} else {
				if (isPhone) {
					
				}else {
					setWebTip(new WebTip(false, "��ȡ��Ϣʧ��", "��ȡ��Ϣʧ��"));
				}
			}
		}
	}
	

	private void deleteBefore(int page, ArrayList<? extends Object> objects) {
		if (objects != null && objects.size() > 0) {
			int beforePage = page--;
			for (int i = 0; i < beforePage * PAGE_SIZE; i++) {
				objects.indexOf(i);
			}
		}
	}

	private ArrayList<News> getNews(int page) {
		mRequest.setAttribute(ParamUtils.ENTITY_TAB, TabType.NEWS);
		mRequest.setAttribute(ParamUtils.ENTITY_PAGE, page);
		return newsDao.getAllNews(page * PAGE_SIZE);
	}

	private ArrayList<Demand> getDemand(int page) {
		mRequest.setAttribute(ParamUtils.ENTITY_TAB, TabType.DEMAND);
		mRequest.setAttribute(ParamUtils.ENTITY_PAGE, page);
		return demandDao.getAllDemands(page * PAGE_SIZE);
	}

	private ArrayList<Supply> getSupply(int page) {
		mRequest.setAttribute(ParamUtils.ENTITY_TAB, TabType.SUPPLY);
		mRequest.setAttribute(ParamUtils.ENTITY_PAGE, page);
		return supplyDao.getAllSupplys(page * PAGE_SIZE);
	}

	private ArrayList<Exhibition> getExhibition(int page) {
		mRequest.setAttribute(ParamUtils.ENTITY_TAB, TabType.EXHIBITON);
		mRequest.setAttribute(ParamUtils.ENTITY_PAGE, page);
		return exhibitonDao.getAllExhibitions(page * PAGE_SIZE);
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