create table if not exists team
(
    id          bigint auto_increment primary key,
    name        varchar(255) not null,
    is_deleted  bit          not null,
    created_at  datetime(6)  not null,
    modified_at datetime(6)  null
);

create table if not exists `users`
(
    id              bigint auto_increment primary key,
    team_id         bigint                                             not null,
    employee_number int                                                not null,
    email           varchar(255)                                       not null,
    name            varchar(255)                                       not null,
    password        varchar(255)                                       not null,
    role            enum ('TEAM_LEADER', 'PART_LEADER', 'TEAM_MEMBER') not null,
    is_deleted      bit                                                not null,
    created_at      datetime(6)                                        not null,
    modified_at     datetime(6)                                        null,
    constraint FKbmqm8c8m2aw1vgrij7h0od0ok foreign key (team_id) references team (id)
);

create table if not exists cp_payment
(
    id          bigint auto_increment primary key,
    user_id     bigint      not null,
    cp          int         not null,
    created_at  datetime(6) not null,
    modified_at datetime(6) null,
    constraint FKd14davkyi64d0lovjebhqody7 foreign key (user_id) references `users` (id)
);

create table if not exists working
(
    id           bigint auto_increment primary key,
    writer_id    bigint                                                       not null,
    cp           int                                                          not null,
    due_date     datetime(6)                                                  not null,
    recruit_date datetime(6)                                                  not null,
    content      varchar(1000)                                                not null,
    title        varchar(255)                                                 not null,
    category     enum ('OTHER', 'DEV')                                        not null,
    status       enum ('WAITING', 'PROCEEDING', 'DONE', 'APPROVAL', 'REJECT') not null,
    is_deleted   bit                                                          not null,
    created_at   datetime(6)                                                  not null,
    modified_at  datetime(6)                                                  null,
    constraint FKho4l2oxr9jw8bvk6vycwo6q8d foreign key (writer_id) references `users` (id)
);

create table if not exists working_assignee
(
    id          bigint auto_increment primary key,
    assignee_id bigint      not null,
    working_id  bigint      not null,
    created_at  datetime(6) not null,
    modified_at datetime(6) null,
    constraint UK_l0vipxu11qifcq9ltmuucy587 unique (working_id),
    constraint FK4mgu1indnefqqdt9eu49locw9 foreign key (working_id) references working (id),
    constraint FKfigvlbwuv0r58vt90t4on9xk7 foreign key (assignee_id) references `users` (id)
);

create table if not exists working_document
(
    id          bigint auto_increment primary key,
    working_id  bigint                                          not null,
    cp          int                                             null,
    title       varchar(255)                                    not null,
    content     varchar(1000)                                   not null,
    link        varchar(255)                                    not null,
    status      enum ('REVIEW', 'APPROVAL_REQUEST', 'APPROVED') not null,
    type        enum ('CONFLUENCE', 'GITLAB')                   not null,
    is_deleted  bit                                             not null,
    created_at  datetime(6)                                     not null,
    modified_at datetime(6)                                     null,
    constraint FK84liff0yjej0nla8gu9jt906q foreign key (working_id) references working (id)
);

create table if not exists document_reviewer
(
    id                  bigint auto_increment primary key,
    reviewer_id         bigint      not null,
    working_document_id bigint      not null,
    created_at          datetime(6) not null,
    modified_at         datetime(6) null,
    constraint UK_document_reviewer unique (working_document_id, reviewer_id),
    constraint FKcuwujt46w1f42d26txobku4ti foreign key (reviewer_id) references `users` (id),
    constraint FKfqia8ykd9upoo9wj95m2ftc2s foreign key (working_document_id) references working_document (id)
);

create table if not exists cp_evaluation
(
    id                   bigint auto_increment primary key,
    cp                   int          not null,
    document_reviewer_id bigint       not null,
    comment              varchar(255) not null,
    is_deleted           bit          not null,
    created_at           datetime(6)  not null,
    modified_at          datetime(6)  null,
    constraint FK5fcf2dg4iu457h2bkjytl84rc foreign key (document_reviewer_id) references document_reviewer (id)
);

create table if not exists review
(
    id                   bigint auto_increment primary key,
    document_reviewer_id bigint                                          not null,
    answer               enum ('GOOD', 'NORMAL', 'BAD')                  not null,
    question             enum ('CLARITY', 'CONSISTENCY', 'COMPLETENESS') not null,
    is_deleted           bit                                             not null,
    created_at           datetime(6)                                     not null,
    modified_at          datetime(6)                                     null,
    constraint FKgm31l3lt859ol519j0o5bubpb foreign key (document_reviewer_id) references document_reviewer (id)
);

