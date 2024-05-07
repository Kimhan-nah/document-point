INSERT INTO team (`is_deleted`, `name`, `created_at`)
VALUES (false, '엔터프라이즈IT개발팀', now());

INSERT INTO `users` (`is_deleted`, `name`, `password`, `employee_number`, `team_id`, `email`, `role`, `created_at`)
VALUES (false, '이파트', 1234, '1111001', 1, 'prolee@hae.com', 'PART_LEADER', now());
INSERT INTO `users` (`is_deleted`, `name`, `password`, `employee_number`, `team_id`, `email`, `role`, `created_at`)
VALUES (false, '김팀장', 1234, '1111002', 1, 'bush@hyundai-autoever.com', 'TEAM_LEADER', now());
INSERT INTO `users` (`is_deleted`, `name`, `password`, `employee_number`, `team_id`, `email`, `role`, `created_at`)
VALUES (false, '최파트', 1234, '1111003', 1, 'prochoi@hae.com', 'PART_LEADER', now());
INSERT INTO `users` (`is_deleted`, `name`, `password`, `employee_number`, `team_id`, `email`, `role`, `created_at`)
VALUES (false, '박멤버', 1234, '1111004', 1, 'jihyun@hyundai-autoever.com', 'TEAM_MEMBER', now());
INSERT INTO `users` (`is_deleted`, `name`, `password`, `employee_number`, `team_id`, `email`, `role`, `created_at`)
VALUES (false, '김멤버', 1234, '1111005', 1, 'mj@hyundai-autoever.com', 'TEAM_MEMBER', now());

