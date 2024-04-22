package com.docpoint.workingdoc.util;

import java.util.List;
import java.util.Map;

import com.docpoint.working.domain.entity.Working;
import com.docpoint.working.domain.type.WorkingStatusType;
import com.docpoint.working.util.WorkingTestData;
import com.docpoint.workingdoc.domain.entity.WorkingDoc;
import com.docpoint.workingdoc.domain.type.DocType;

public class WorkingDocTestData {
	public static WorkingDoc createWorkingDoc() {
		Working working = WorkingTestData.createWorking();
		working = working.updateStatus(WorkingStatusType.DONE);
		return new WorkingDoc(working, "title", "content", createLinks());
	}

	public static Map<DocType, List<String>> createLinks() {
		return Map.of(DocType.CONFLUENCE, List.of("confluence.com"), DocType.GITLAB, List.of("gitlab.com"));
	}

	public static Map<DocType, List<String>> createLinks(DocType docType, List<String> links) {
		return Map.of(docType, links);
	}
}
