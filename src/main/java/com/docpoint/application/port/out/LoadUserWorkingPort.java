package com.docpoint.application.port.out;

import java.util.List;

import com.docpoint.domain.entity.Working;

public interface LoadUserWorkingPort {
	List<Working> loadByTitle(long userId, String search);
}
