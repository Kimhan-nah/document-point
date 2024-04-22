# 목차
- [목차](#목차)
- [기능 명세](#기능-명세)
    - [Working (수행 워킹)](#working-수행-워킹)
    - [WorkingDocument (워킹 문서)](#workingdocument-워킹-문서)
    - [DocumentReviewr (문서 리뷰어)](#documentreviewr-문서-리뷰어)
    - [DocumentReview (문서 리뷰)](#documentreview-문서-리뷰)


# 기능 명세

### Working (수행 워킹)

- [ ] Working은 작성자, 담당자, 기여도(cp), 제목, 내용, 삭제 여부, 상태, 종류, 마감일, 지원 마감일을 갖는다.
- [ ] 담당자를 제외한 모든 정보는 필수로 입력해야 한다.
- [ ] 완료 여부를 체크할 수 있다.
- [ ] Working은 등록이 가능하다.
- [ ] Working은 담당자 등록이 가능하다.
- [ ] Working은 삭제가 가능하다. (삭제 여부를 true로 변경)

> 이미 만들어진 기능이므로 사용하지 않는 기능 명세는 생략한다. (상태, 종류에 대한 enum type도 생략)

### WorkingDocument (워킹 문서)

- [ ] WorkingDocument는 수행 워킹, 제목, 내용, 상태, 삭제 여부, 문서 링크를 가진다.
- [ ] 링크는 1개 이상의 url이 필수로 입력되어야 한다.
- [ ] working의 초기 상태는 '검토중'이다.
- [ ] 삭제 여부는 false로 초기화된다.
- [ ] 수행 워킹의 담당자는 본인이 수행한 워킹에 대한 WorkingDocument는 등록이 가능하다.
- [ ] Working의 상태가 '대기'일 경우 등록이 불가능하다.
- [ ] 수행 워킹의 담당자는 WorkingDocument 삭제가 가능하다. (삭제 여부를 true로 변경)
- [ ] 수행 워킹의 담당자는 WorkingDocument 수정이 가능하다.
- [ ] WorkingDocument에 대한 review가 1개 이상이면 수정/삭제가 불가능하다.
- [ ] WorkingDocument는 상태를 변경할 수 있다.
- [ ] 본인이 작성한 WorkingDocument 조회가 가능하다.
- [ ] 파트 리더, 팀 리더는 팀 멤버의 WorkingDocument 조회가 가능하다.

### DocumentReviewr (문서 리뷰어)

### DocumentReview (문서 리뷰)

