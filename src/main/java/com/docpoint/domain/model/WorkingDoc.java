package com.docpoint.domain.model;

import java.util.List;
import java.util.Map;

import com.docpoint.domain.type.WorkingStatusType;
import com.docpoint.domain.type.DocStatusType;
import com.docpoint.domain.type.DocType;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class WorkingDoc {
	private final Working working;

	private final String title;

	private final String content;

	private final DocStatusType status;

	private final boolean isDeleted;

	private final Map<DocType, List<String>> links;

	private WorkingDoc(@NonNull Working working, @NonNull String title, @NonNull String content,
		@NonNull DocStatusType status, boolean isDeleted, @NonNull Map<DocType, List<String>> links) {
		checkWorkingStatus(working.getStatus());
		checkLinks(links);
		this.working = working;
		this.title = title;
		this.content = content;
		this.status = status;
		this.isDeleted = isDeleted;
		this.links = links;
	}

	/**
	 * <p> isDeleted는 false로 초기화 된다. </p>
	 * <p> status는 REVIEW(검토중)로 초기화 된다. </p>
	 */
	public WorkingDoc(@NonNull Working working, @NonNull String title, @NonNull String content,
		@NonNull Map<DocType, List<String>> links) {
		this(working, title, content, DocStatusType.REVIEW, false, links);
	}

	private static void checkWorkingStatus(WorkingStatusType status) {
		if (status == WorkingStatusType.WAITING) {
			throw new IllegalArgumentException("대기 상태인 업무만 문서를 생성할 수 있습니다.");
		}
	}

	private static void checkLinks(Map<DocType, List<String>> links) {
		int urlCount = 0;
		final int MIN_URL_COUNT = 1;

		for (Map.Entry<DocType, List<String>> entry : links.entrySet()) {
			if (entry.getValue() == null) {
				throw new NullPointerException("links는 null이 될 수 없습니다.");
			}
			urlCount += entry.getValue().size();
		}

		if (urlCount < MIN_URL_COUNT) {
			throw new IllegalArgumentException("links의 url 갯수가 부족하여 문서를 생성할 수 없습니다.");
		}
	}
}
