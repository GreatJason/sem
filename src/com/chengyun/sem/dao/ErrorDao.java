package com.chengyun.sem.dao;

import java.util.List;

import com.chengyun.sem.model.ErrorInfo;

public interface ErrorDao {
	/**
	 * get all Error Info
	 * @return
	 */
	List<ErrorInfo> getErrors();
}
