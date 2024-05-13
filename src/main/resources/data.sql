# Team
# INSERT INTO team (`name`) VALUE ('엔터프라이즈IT개발팀');

SET @team_leader_id = 1;
SET @part_leader_id_1 = 2;
SET @part_leader_id_2 = 3;
SET @member_id_1 = 4;
SET @member_id_2 = 5;
SET @member_id_3 = 6;
SET @member_id_4 = 7;
SET @member_id_5 = 8;
SET @member_id_6 = 9;

# Working 40개 생성 : APPROVAL 20개, DONE 10개, PROCEEDING 10개
-- 1 (APPROVAL 20개)
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@team_leader_id, 800, '2023-01-30', '2023-01-01', '내용1', '수행 워킹(업무) 개발1', 'DEV', 'APPROVAL');
-- 2
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@team_leader_id, 1000, '2023-01-30', '2023-01-15', '내용2', '수행 워킹(업무) 개발2', 'OTHER', 'APPROVAL');
-- 3
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@team_leader_id, 1200, '2023-02-27', '2023-02-10', '내용3', '수행 워킹(업무) 개발3', 'DEV', 'APPROVAL');
-- 4
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@team_leader_id, 900, '2023-03-03', '2023-02-15', '내용4', '수행 워킹(업무) 개발4', 'DEV', 'APPROVAL');
-- 5
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@team_leader_id, 1100, '2023-04-15', '2023-03-20', '내용5', '수행 워킹(업무) 개발5', 'OTHER', 'APPROVAL');
-- 6
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 1000, '2023-04-25', '2023-03-25', '내용6', '수행 워킹(업무) 개발6', 'DEV', 'APPROVAL');
-- 7
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 1200, '2023-04-20', '2023-04-01', '내용7', '수행 워킹(업무) 개발7', 'DEV', 'APPROVAL');
-- 8
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 800, '2023-04-30', '2023-04-05', '내용8', '수행 워킹(업무) 개발8', 'OTHER', 'APPROVAL');
-- 9
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 1100, '2023-05-25', '2023-05-10', '내용9', '수행 워킹(업무) 개발9', 'DEV', 'APPROVAL');
-- 10
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 1000, '2023-07-10', '2023-06-15', '내용10', '수행 워킹(업무) 개발10', 'DEV', 'APPROVAL');
-- 11
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 1200, '2023-08-25', '2023-07-20', '내용11', '이달의 테크톡 진행 11', 'OTHER', 'APPROVAL');
-- 12
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 900, '2023-09-10', '2023-07-25', '내용12', '이달의 테크톡 진행 12', 'DEV', 'APPROVAL');
-- 13
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 1000, '2023-09-25', '2023-08-30', '내용13', '이달의 테크톡 진행 13', 'OTHER', 'APPROVAL');
-- 14
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 1200, '2023-10-10', '2023-09-05', '내용14', '이달의 테크톡 진행 14', 'DEV', 'APPROVAL');
-- 15
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 800, '2023-10-25', '2023-09-10', '내용15', '이달의 테크톡 진행 15', 'OTHER', 'APPROVAL');
-- 16
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1100, '2023-11-05', '2023-10-15', '내용16', '보안의 날 행사 진행 16', 'DEV', 'APPROVAL');
-- 17
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1000, '2023-12-20', '2023-11-20', '내용17', '보안의 날 행사 진행 17', 'DEV', 'APPROVAL');
-- 18
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1200, '2023-12-25', '2023-11-25', '내용18', '보안의 날 행사 진행 18', 'OTHER', 'APPROVAL');
-- 19
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 900, '2024-01-20', '2023-12-01', '내용19', '보안의 날 행사 진행 19', 'DEV', 'APPROVAL');
-- 20
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1100, '2024-01-05', '2023-12-05', '내용20', '보안의 날 행사 진행 20', 'DEV', 'APPROVAL');

-- 21 (DONE 10개)
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 1000, '2024-01-20', '2023-12-01', '내용21', '보안의 날 행사 진행 21', 'DEV', 'DONE');
-- 22
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 1200, '2024-01-05', '2023-12-05', '내용22', '보안의 날 행사 진행 22', 'DEV', 'DONE');
-- 23
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 900, '2024-02-20', '2024-01-01', '내용23', '보안의 날 행사 진행 23', 'DEV', 'DONE');
-- 24
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 1100, '2024-02-05', '2024-01-05', '내용24', '보안의 날 행사 진행 24', 'DEV', 'DONE');
-- 25
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_1, 1000, '2024-03-20', '2024-02-01', '내용25', '보안의 날 행사 진행 25', 'DEV', 'DONE');
-- 26
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1200, '2024-03-05', '2024-02-05', '내용26', '보안의 날 행사 진행 26', 'DEV', 'DONE');
-- 27
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 900, '2024-04-20', '2024-03-01', '내용27', '보안의 날 행사 진행 27', 'DEV', 'DONE');
-- 28
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1100, '2024-04-05', '2024-03-05', '내용28', '보안의 날 행사 진행 28', 'DEV', 'DONE');
-- 29
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1000, '2024-05-20', '2024-04-01', '내용29', '보안의 날 행사 진행 29', 'DEV', 'DONE');
-- 30
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1200, '2024-05-05', '2024-04-05', '내용30', '보안의 날 행사 진행 30', 'DEV', 'DONE');

-- 31 (PROCEEDING 10개)
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 900, '2024-06-20', '2024-04-01', '내용31', '문서 관리 모듈 백엔드 개발 31', 'DEV', 'PROCEEDING');
-- 32
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1100, '2024-06-05', '2024-04-05', '내용32', '문서 관리 모듈 백엔드 개발 32', 'DEV', 'PROCEEDING');
-- 33
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1000, '2024-07-20', '2024-04-01', '내용33', '문서 관리 모듈 백엔드 개발 33', 'DEV', 'PROCEEDING');
-- 34
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1200, '2024-07-05', '2024-04-05', '내용34', '문서 관리 모듈 백엔드 개발 34', 'DEV', 'PROCEEDING');
-- 35
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 900, '2024-08-20', '2024-04-01', '내용35', '문서 관리 모듈 백엔드 개발 35', 'DEV', 'PROCEEDING');
-- 36
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1100, '2024-08-05', '2024-05-05', '내용36', '문서 관리 모듈 프론트 개발 36', 'DEV', 'PROCEEDING');
-- 37
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1000, '2024-09-20', '2024-05-01', '내용37', '문서 관리 모듈 프론트 개발 37', 'DEV', 'PROCEEDING');
-- 38
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1200, '2024-09-05', '2024-05-05', '내용38', '문서 관리 모듈 프론트 개발 38', 'DEV', 'PROCEEDING');
-- 39
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 900, '2024-10-20', '2024-05-01', '내용39', '문서 관리 모듈 프론트 개발 39', 'DEV', 'PROCEEDING');
-- 40
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`)
    VALUE (@part_leader_id_2, 1100, '2024-10-05', '2024-05-05', '내용40', '문서 관리 모듈 프론트 개발 40', 'DEV', 'PROCEEDING');

# WorkingAssignee 40개 생성
DROP PROCEDURE IF EXISTS createWorkingAssignee;
DELIMITER $$
CREATE PROCEDURE createWorkingAssignee()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 40
        DO
            INSERT INTO `working_assignee` (`assignee_id`, `working_id`)
            SELECT IF(MOD(i, 2) = 0, @member_id_1, @member_id_2) AS assignee_id,
                   i                                             AS working_id;
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER ;
CALL createWorkingAssignee();


# WorkingDocument
# member_id_1의 APPROVED 20개, member_id_2의 APPROVED 10개, member_id_3의 APPROVED 10개

DROP PROCEDURE IF EXISTS createWorkingDocument1;
DELIMITER $$
CREATE PROCEDURE createWorkingDocument1()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 10
        DO
            INSERT INTO `working_document` (`working_id`, `cp`, `title`, `content`, `link`, `status`, `type`,
                                            `created_at`)
                VALUE (i, null, CONCAT('수행 워킹(업무) 개발 ', i, ' 문서화 진행했습니다.'),
                       CONCAT('수행 워킹(업무) 개발 ', i, ' 문서화 진행했습니다. JavaDoc 문서화 및 마크다운 추가했습니다. 확인 부탁드립니다 :)'),
                       'https://gitlab.com/jihankim', 'APPROVED', 'GITLAB', CONCAT('2024-01-', i + 10));
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER ;
CALL createWorkingDocument1();

DROP PROCEDURE IF EXISTS createWorkingDocument2;
DELIMITER $$
CREATE PROCEDURE createWorkingDocument2()
BEGIN
    DECLARE i INT DEFAULT 11;
    WHILE i <= 15
        DO
            INSERT INTO `working_document` (`working_id`, `cp`, `title`, `content`, `link`, `status`, `type`,
                                            `created_at`)
                VALUE (i, null, CONCAT('이달의 테크톡 ', i, ' 자료 정리하였습니다.'),
                       CONCAT('이달의 테크톡 ', i, ' 자료 정리하였습니다. 자세한 내용은 링크로 확인 부탁드립니다. :)'),
                       'https://confluence.com', 'APPROVED', 'CONFLUENCE', CONCAT('2024-01-', i));
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER ;
CALL createWorkingDocument2();

DROP PROCEDURE IF EXISTS createWorkingDocument3;
DELIMITER $$
CREATE PROCEDURE createWorkingDocument3()
BEGIN
    DECLARE i INT DEFAULT 16;
    WHILE i <= 20
        DO
            INSERT INTO `working_document` (`working_id`, `cp`, `title`, `content`, `link`, `status`, `type`,
                                            `created_at`)
                VALUE (i, 1100, CONCAT('보안의 날 행사 ', i, ' 자료 정리하였습니다.'),
                       CONCAT('보안의 날 행사 ', i, ' 자료 정리하였습니다. 자세한 내용은 링크로 확인 부탁드립니다. :)'),
                       'https://confluence.com', 'APPROVED', 'CONFLUENCE', CONCAT('2024-02-', i));
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER ;
CALL createWorkingDocument3();

DROP PROCEDURE IF EXISTS createWorkingDocument4;
DELIMITER $$
CREATE PROCEDURE createWorkingDocument4()
BEGIN
    DECLARE i INT DEFAULT 21;
    WHILE i <= 30
        DO
            INSERT INTO `working_document` (`working_id`, `cp`, `title`, `content`, `link`, `status`, `type`,
                                            `created_at`)
                VALUE (i, 1000, CONCAT('보안의 날 행사 ', i, ' 자료 정리하였습니다.'),
                       CONCAT('보안의 날 행사 ', i, ' 자료 정리하였습니다. 자세한 내용은 링크로 확인 부탁드립니다. :)'),
                       'https://confluence.com', 'APPROVAL_REQUEST', 'CONFLUENCE', CONCAT('2024-03-', i));
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER ;
CALL createWorkingDocument4();

DROP PROCEDURE IF EXISTS createWorkingDocument5;
DELIMITER $$
CREATE PROCEDURE createWorkingDocument5()
BEGIN
    DECLARE i INT DEFAULT 31;
    WHILE i <= 40
        DO
            INSERT INTO `working_document` (`working_id`, `cp`, `title`, `content`, `link`, `status`, `type`,
                                            `created_at`)
                VALUE (i, 900, CONCAT('문서 관리 모듈 개발 ', i, ' 문서화 진행했습니다.'),
                       CONCAT('문서 관리 모듈 개발 ', i, ' 문서화 진행했습니다. JavaDoc 문서화 및 마크다운 추가했습니다. 확인 부탁드립니다 :)'),
                       'https://gitlab.com/jihankim', 'REVIEW', 'GITLAB', CONCAT('2024-04-', i - 30));
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER ;
CALL createWorkingDocument5();

# DocumentReviewer
# Team Leader와 Part Leader는 모두 생성
# 6 * 40 = 240개 생성
DROP PROCEDURE IF EXISTS createDocumentReviewer;
DELIMITER $$
CREATE PROCEDURE createDocumentReviewer()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 40
        DO
            INSERT INTO `document_reviewer` (`reviewer_id`, `working_document_id`)
                VALUE (@team_leader_id, i);
            INSERT INTO `document_reviewer` (`reviewer_id`, `working_document_id`)
                VALUE (@part_leader_id_1, i);
            INSERT INTO `document_reviewer` (`reviewer_id`, `working_document_id`)
                VALUE (@part_leader_id_2, i);
            INSERT INTO `document_reviewer` (`reviewer_id`, `working_document_id`)
            SELECT IF(MOD(i, 2) = 0, @member_id_2, @member_id_1) AS reviewer_id,
                   i                                             AS working_document_id;

            INSERT INTO `document_reviewer` (`reviewer_id`, `working_document_id`)
                VALUE (@member_id_3, i);
            INSERT INTO `document_reviewer` (`reviewer_id`, `working_document_id`)
                VALUE (@member_id_4, i);
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER ;
CALL createDocumentReviewer();

# cp evaluation
DROP PROCEDURE IF EXISTS createCpEvaluationAndReview;
DELIMITER $$
CREATE PROCEDURE createCpEvaluationAndReview()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 20
        DO
            INSERT INTO `review` (`document_reviewer_id`, `answer`, `question`)
                VALUE (@part_leader_id_1, 'BAD', 'CLARITY');
            INSERT INTO `review` (`document_reviewer_id`, `answer`, `question`)
                VALUE (@part_leader_id_1, 'NORMAL', 'CONSISTENCY');
            INSERT INTO `review` (`document_reviewer_id`, `answer`, `question`)
                VALUE (@part_leader_id_1, 'GOOD', 'COMPLETENESS');
            INSERT INTO `cp_evaluation` (`cp`, `document_reviewer_id`, `comment`)
                VALUE (1000, @part_leader_id_1,
                       '기여도에 대한 코멘트입니다. 명확성은 부족하지만 완성도가 좋았습니다. 문서화가 잘 되어있어서 좋았고 중요한 부분이라고 생각되어 1000으로 책정하였습니다.');
            INSERT INTO `cp_evaluation` (`cp`, `document_reviewer_id`, `comment`)
                VALUE (1200, @team_leader_id,
                       '기여도에 대한 코멘트입니다. 완성도와 퀄리티가 너무 좋네요. 꼼꼼하게 잘 작성하신 것 같아서 더 높은 기여도로 승인합니다. :)');
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER ;
CALL createCpEvaluationAndReview();

DROP PROCEDURE IF EXISTS createCpEvaluationAndReview2;
DELIMITER $$
CREATE PROCEDURE createCpEvaluationAndReview2()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 10
        DO
            INSERT INTO `review` (`document_reviewer_id`, `answer`, `question`)
                VALUE (i * 6 + 2, 'BAD', 'CLARITY');
            INSERT INTO `review` (`document_reviewer_id`, `answer`, `question`)
                VALUE (i * 6 + 2, 'NORMAL', 'CONSISTENCY');
            INSERT INTO `review` (`document_reviewer_id`, `answer`, `question`)
                VALUE (i * 6 + 2, 'GOOD', 'COMPLETENESS');
            INSERT INTO `cp_evaluation` (`cp`, `document_reviewer_id`, `comment`)
                VALUE (1000, i * 6 + 2,
                       '기여도에 대한 코멘트입니다. 명확성은 부족하지만 완성도가 좋았습니다. 문서화가 잘 되어있어서 좋았고 중요한 부분이라고 생각되어 1000으로 책정하였습니다.');
            SET i = i + 1;
        END WHILE;
END$$
DELIMITER ;
CALL createCpEvaluationAndReview2();
