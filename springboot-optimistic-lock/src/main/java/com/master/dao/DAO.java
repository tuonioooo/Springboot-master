package com.master.dao;

public interface DAO {

	/**
	 * 保存对象
	 * @param str
	 * @param obj
	 * @return
	 */
	public Object save(String str, Object obj);

	/**
	 * 修改对象
	 * @param str
	 * @param obj
	 * @return
	 */
	public Object update(String str, Object obj);

	/**
	 * 删除对象 
	 * @param str
	 * @param obj
	 * @return
	 */
	public Object delete(String str, Object obj);

	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 */
	public Object findForObject(String str, Object obj);

	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 */
	public Object findForList(String str, Object obj);

	/**
	 * 查找对象封装成Map
	 * @param s
	 * @param obj
	 * @return
	 */
	public Object findForMap(String sql, Object obj, String key, String value);

}
