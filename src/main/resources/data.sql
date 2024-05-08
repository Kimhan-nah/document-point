# Team
INSERT INTO team (`is_deleted`, `name`, `created_at`)
    VALUE (false, '엔터프라이즈IT개발팀', now());
SET @team_id = LAST_INSERT_ID();

# User
-- 1
INSERT INTO `users` (`is_deleted`, `name`, `password`, employee_id, `team_id`, `email`, `role`, `created_at`)
    VALUE (false, '이파트', 1234, '1111001', @team_id, 'prolee@hae.com', 'PART_LEADER', now());
-- 2
INSERT INTO `users` (`is_deleted`, `name`, `password`, employee_id, `team_id`, `email`, `role`, `created_at`)
    VALUE (false, '김팀장', 1234, '1111002', @team_id, 'bush@hyundai-autoever.com', 'TEAM_LEADER', now());
-- 3
INSERT INTO `users` (`is_deleted`, `name`, `password`, employee_id, `team_id`, `email`, `role`, `created_at`)
    VALUE (false, '최파트', 1234, '1111003', @team_id, 'prochoi@hae.com', 'PART_LEADER', now());
-- 4
INSERT INTO `users` (`is_deleted`, `name`, `password`, employee_id, `team_id`, `email`, `role`, `created_at`)
    VALUE (false, '박멤버', 1234, '1111004', @team_id, 'jihyun@hyundai-autoever.com', 'TEAM_MEMBER', now());
-- 5
INSERT INTO `users` (`is_deleted`, `name`, `password`, employee_id, `team_id`, `email`, `role`, `created_at`)
    VALUE (false, '김멤버', 1234, '1111005', @team_id, 'mj@hyundai-autoever.com', 'TEAM_MEMBER', now());

SET @part_leader_id_1 = LAST_INSERT_ID() - 4;
SET @team_leader_id_1 = LAST_INSERT_ID() - 3;
SET @part_leader_id_2 = LAST_INSERT_ID() - 2;
SET @member_id_1 = LAST_INSERT_ID() - 1;
SET @member_id_2 = LAST_INSERT_ID();

# Working
## MEMBER 1의 Working
-- 1
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 800, '2024-06-30', '2024-06-01', '내용1', '제목1', 'DEV', 'WAITING', false, now());
-- 2
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 1000, '2024-07-15', '2024-06-05', '내용2', '제목2', 'OTHER', 'PROCEEDING', false, now());
-- 3
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 1200, '2024-08-10', '2024-06-10', '내용3', '제목3', 'DEV', 'WAITING', false, now());
-- 4
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 900, '2024-07-20', '2024-06-15', '내용4', '제목4', 'DEV', 'PROCEEDING', false, now());
-- 5
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 1100, '2024-08-05', '2024-06-20', '내용5', '제목5', 'OTHER', 'DONE', false, now());
-- 6
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 1000, '2024-07-25', '2024-06-25', '내용6', '제목6', 'DEV', 'WAITING', false, now());
-- 7
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 1200, '2024-08-20', '2024-07-01', '내용7', '제목7', 'DEV', 'PROCEEDING', false, now());
-- 8
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 800, '2024-07-30', '2024-07-05', '내용8', '제목8', 'OTHER', 'DONE', false, now());
-- 9
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 1100, '2024-08-25', '2024-07-10', '내용9', '제목9', 'DEV', 'WAITING', false, now());
-- 10
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 1000, '2024-08-10', '2024-07-15', '내용10', '제목10', 'DEV', 'PROCEEDING', false, now());
-- 11
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 1200, '2024-09-05', '2024-07-20', '내용11', '제목11', 'OTHER', 'WAITING', false, now());
-- 12
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 900, '2024-09-20', '2024-07-25', '내용12', '제목12', 'DEV', 'DONE', false, now());
-- 13
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 1000, '2024-09-15', '2024-07-30', '내용13', '제목13', 'OTHER', 'PROCEEDING', false, now());
-- 14
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 1200, '2024-10-10', '2024-08-05', '내용14', '제목14', 'DEV', 'WAITING', false, now());
-- 15
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 800, '2024-10-25', '2024-08-10', '내용15', '제목15', 'OTHER', 'PROCEEDING', false, now());
-- 16
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_1, 1100, '2024-11-05', '2024-08-15', '내용16', '제목16', 'DEV', 'DONE', false, now());

SET @member_1_working_id_1 = LAST_INSERT_ID() - 15;
SET @member_1_working_id_2 = LAST_INSERT_ID() - 14;
SET @member_1_working_id_3 = LAST_INSERT_ID() - 13;
SET @member_1_working_id_4 = LAST_INSERT_ID() - 12;
SET @member_1_working_id_5 = LAST_INSERT_ID() - 11;
SET @member_1_working_id_6 = LAST_INSERT_ID() - 10;
SET @member_1_working_id_7 = LAST_INSERT_ID() - 9;
SET @member_1_working_id_8 = LAST_INSERT_ID() - 8;
SET @member_1_working_id_9 = LAST_INSERT_ID() - 7;
SET @member_1_working_id_10 = LAST_INSERT_ID() - 6;
SET @member_1_working_id_11 = LAST_INSERT_ID() - 5;
SET @member_1_working_id_12 = LAST_INSERT_ID() - 4;
SET @member_1_working_id_13 = LAST_INSERT_ID() - 3;
SET @member_1_working_id_14 = LAST_INSERT_ID() - 2;
SET @member_1_working_id_15 = LAST_INSERT_ID() - 1;
SET @member_1_working_id_16 = LAST_INSERT_ID();

## MEMBER 2의 Working
-- 1
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_2, 1200, '2021-06-30', '2021-06-01', '내용1', '제목1', 'DEV', 'WAITING', false, now());
-- 2
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_2, 1000, '2021-06-30', '2021-06-01', '내용1', '제목1', 'OTHER', 'PROCEEDING', false, now());
-- 3
INSERT INTO `working` (`writer_id`, `cp`, `due_date`, `recruit_date`, `content`, `title`, `category`, `status`,
                       `is_deleted`, `created_at`)
    VALUE (@member_id_2, 1200, '2021-06-30', '2021-06-01', '내용1', '제목1', 'DEV', 'DONE', false, now());

SET @member_2_working_id_1 = LAST_INSERT_ID() - 2;
SET @member_2_working_id_2 = LAST_INSERT_ID() - 1;
SET @member_2_working_id_3 = LAST_INSERT_ID();


# WorkingAssignee
-- MEMBER 1의 WorkingAssignee
-- 1
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_1, now());
-- 2
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_2, now());
-- 3
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_3, now());
-- 4
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_4, now());
-- 5
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_5, now());
-- 6
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_6, now());
-- 7
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_7, now());
-- 8
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_8, now());
-- 9
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_9, now());
-- 10
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_10, now());
-- 11
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_11, now());
-- 12
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_12, now());
-- 13
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_13, now());
-- 14
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_14, now());
-- 15
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_15, now());
-- 16
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_1, @member_1_working_id_16, now());

-- MEMBER 2의 WorkingAssignee
-- 1
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_2, @member_2_working_id_1, now());
-- 2
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_2, @member_2_working_id_2, now());
-- 3
INSERT INTO `working_assignee` (`assignee_id`, `working_id`, `created_at`)
    VALUE (@member_id_2, @member_2_working_id_3, now());


