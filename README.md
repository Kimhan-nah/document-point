# 목차

- [목차](#목차)
- [기능 명세](#기능-명세)
    - [Working (수행 워킹)](#working-수행-워킹)
    - [WorkingDocument (워킹 문서)](#workingdocument-워킹-문서)
    - [DocumentReviewr (문서 리뷰어)](#documentreviewr-문서-리뷰어)
    - [DocumentReview (문서 리뷰)](#documentreview-문서-리뷰)

# 기능 명세

## Working (수행 워킹)

- [ ] Working은 작성자, 담당자, 기여도(cp), 제목, 내용, 삭제 여부, 상태, 종류, 마감일, 지원 마감일을 갖는다.
- [ ] 담당자를 제외한 모든 정보는 필수로 입력해야 한다.
- [ ] 완료 여부를 체크할 수 있다.
- [ ] Working은 등록이 가능하다.
- [ ] Working은 담당자 등록이 가능하다.

> 이미 만들어진 기능이므로 사용하지 않는 기능 명세는 생략한다. (상태, 종류에 대한 enum type도 생략)

## WorkingDocument (워킹 문서)

### Domain Model

- [x] WorkingDocument는 수행 워킹, 제목, 내용, 상태, 삭제 여부, 문서 링크를 가진다.

---

### 등록 Use Case

[//]: # (adapter level)

- [ ] working이 존재해야 한다. <- working 조회 use case
- [ ] 수행 워킹의 담당자가 본인이 수행한 워킹에 대한 WorkingDocument는 등록이 가능하다.
- [ ] 이미 Working에 대한 요청된 WorkingDocument가 있을 경우 등록이 불가능하다.
    - X!! 가능하다.
    - 일단 해당 검증은 생략.. 등록 기능이 이미 구현된 사항이라면 중요하지 않은 것 같다..

[//]: # (service level)

- [x] 삭제된 working에 대한 workingDocument는 등록이 불가능하다.
- [x] Working의 상태가 '대기(WAITING)'일 경우 등록이 불가능하다.

- [x] 링크는 1개 이상의 url이 필수로 입력되어야 한다.
- [x] WorkingDocument의 초기 상태인 '검토중(REVIEW)'이 아닌 상태로 등록이 불가능하다.
- [ ] 리뷰어가 1명 이상이어야 한다. <- TODO 확인 필요

[//]: # (out adapter\(port\) level)

- [ ] Reviewer 저장
- [ ] DocLink 저장
- [x] WorkingDocument 저장

### 수정 및 삭제 Use Case

- [ ] 수행 워킹의 담당자는 WorkingDocument 삭제가 가능하다. (삭제 여부를 true로 변경)
- [ ] 수행 워킹의 담당자는 WorkingDocument 수정이 가능하다.
- [ ] WorkingDocument에 대한 review가 1개 이상이면 수정/삭제가 불가능하다.
- [ ] WorkingDocument는 상태를 변경할 수 있다.
- [ ] 본인이 작성한 WorkingDocument 조회가 가능하다.
- [ ] 파트 리더, 팀 리더는 팀 멤버의 WorkingDocument 조회가 가능하다.

### 전체 요청 조회 Use Case (PART_LEADER, TEAM_LEADER)

- [x] 완료

### 내 요청 조회 Use Case (TEAM_MEMBER)

- [x] 완료

### 받은 요청 조회 Use case (ALL)

- [ ] 완료

### 상세 조회 Use Case (ALL)

## DocumentReview (문서 리뷰)


