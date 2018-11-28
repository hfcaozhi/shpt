/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/6/3 11:31:46                            */
/*==============================================================*/


drop table if exists car;

drop table if exists crim;

drop table if exists event;

drop table if exists fuel_record;

drop table if exists law;

drop table if exists mega_eyes;

drop table if exists member;

drop table if exists mend_record;

drop table if exists menu;

drop table if exists organization;

drop table if exists resource;

drop table if exists role;

drop table if exists role_resource_ref;

drop table if exists term;

drop table if exists user;

drop table if exists user_menu;

drop table if exists user_org_ref;

drop table if exists user_role_ref;

/*==============================================================*/
/* Table: car                                                   */
/*==============================================================*/
create table car
(
   id                   bigint not null auto_increment,
   car_no               varchar(40),
   fuel_card_no         varchar(40),
   gps_no               varchar(40),
   org_id               bigint,
   `desc`               varchar(200),
   primary key (id)
);

/*==============================================================*/
/* Table: crim                                                  */
/*==============================================================*/
create table crim
(
   id                   bigint not null auto_increment,
   name                 varchar(20),
   sex                  tinyint default 0 comment '0 男
            1 女',
   un_id                varchar(40),
   phone                varchar(30),
   motor_id             varchar(40),
   address              varchar(200),
   temp_address         varchar(200),
   `condition`          varchar(20),
   type                 tinyint comment '0 个人
            1 法人或组织',
   firm_name            varchar(80),
   person_in_law        varchar(20),
   reg_address          varchar(200),
   open_address         varchar(200),
   business_license     varchar(100),
   firm_type            varchar(20),
   agent                varchar(20),
   primary key (id)
);

/*==============================================================*/
/* Table: event                                                 */
/*==============================================================*/
create table event
(
   id                   bigint not null auto_increment,
   code                 varchar(40),
   source               varchar(40),
   org_id               bigint,
   create_time          datetime,
   reporter             varchar(20),
   description          varchar(200),
   oper_address         varchar(200),
   process              varchar(20),
   law                  bigint,
   term                 bigint,
   process_method       varchar(20),
   method_intro         varchar(200),
   end_time             datetime,
   illegal              varchar(200),
   resule               varchar(40),
   crim_id              bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: fuel_record                                           */
/*==============================================================*/
create table fuel_record
(
   id                   bigint not null auto_increment,
   car_id               bigint,
   name                 varchar(40),
   card_type            varchar(20),
   fuel_card_no         varchar(40),
   fuel_card_inner      varchar(40),
   create_time          datetime,
   L                    float(4,2),
   cost                 float(6,2),
   mileage              varchar(20),
   card_value           float(6,2),
   memo                 varchar(600),
   primary key (id)
);

/*==============================================================*/
/* Table: law                                                   */
/*==============================================================*/
create table law
(
   id                   bigint not null auto_increment,
   name                 varchar(200),
   primary key (id)
);

/*==============================================================*/
/* Table: mega_eyes                                             */
/*==============================================================*/
create table mega_eyes
(
   id                   bigint not null auto_increment,
   name                 varchar(40),
   address              varchar(200),
   description          varchar(200),
   x                    varchar(40),
   y                    varchar(40),
   primary key (id)
);

alter table mega_eyes comment '定义探头的所在位置 ';

/*==============================================================*/
/* Table: member                                                */
/*==============================================================*/
create table member
(
   id                   bigint not null auto_increment,
   name                 varchar(20),
   login                varchar(40),
   description          varchar(200),
   status               tinyint comment '0在职 
            1离职
            2休假',
   category             tinyint comment '0 执法人员
            1 办公人员',
   exe_code             varchar(40),
   expire_time          date,
   breast_piece         varchar(40),
   grade                varchar(20),
   duties               varchar(20),
   org_id               bigint,
   sex                  tinyint comment '0 男
            1 女',
   phone                varchar(20),
   tel                  varchar(20),
   un_id                varbinary(40),
   primary key (id)
);

/*==============================================================*/
/* Table: mend_record                                           */
/*==============================================================*/
create table mend_record
(
   id                   bigint not null auto_increment,
   car_id               bigint,
   create_time          date,
   cost                 float(6,2),
   description          varchar(200),
   mend_user            varchar(40),
   primary key (id)
);

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   id                   bigint not null,
   name                 varchar(40),
   description          varchar(200),
   parent_id            bigint,
   code_no              bigint comment '菜单总个数是一定的。将系统中所用到的菜单都定义一个唯一的编号，供前台做逻辑判断。菜单只提供修改名称操作。',
   porder               bigint comment '显示顺序，值越大排得越靠前',
   status               tinyint comment '0正常1禁用2删除',
   resource             bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: organization                                          */
/*==============================================================*/
create table organization
(
   id                   bigint not null auto_increment,
   name                 varchar(40),
   description          varchar(40),
   parent_id            bigint,
   primary key (id)
);

alter table organization comment '根组织不能删除';

/*==============================================================*/
/* Table: resource                                              */
/*==============================================================*/
create table resource
(
   id                   bigint not null auto_increment,
   name                 varchar(200),
   action_url           varchar(200),
   description          varchar(400),
   status               tinyint comment '0正常
            1禁用
            2删除',
   is_leaf              tinyint comment '0非1是',
   parent_id            bigint,
   primary key (id)
);

alter table resource comment '系统中所有的资源。以树状结构来组织。所有的非叶子节点都属于组的概念，都没有实质的action_url只有它的子节点才有。';

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   bigint not null auto_increment,
   name                 varchar(40),
   description          varchar(40),
   status               tinyint comment '0正常
            1禁用
            2删除',
   primary key (id)
);

/*==============================================================*/
/* Table: role_resource_ref                                     */
/*==============================================================*/
create table role_resource_ref
(
   id                   bigint not null auto_increment,
   role_id              bigint,
   resource_id          bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: term                                                  */
/*==============================================================*/
create table term
(
   id                   bigint not null auto_increment,
   name                 varchar(800),
   law_id               bigint,
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   bigint not null auto_increment,
   user_name            varchar(40),
   password             varchar(40),
   status               tinyint comment '0正常
            1禁用
            2删除',
   nick_name            varchar(40),
   primary key (id)
);

alter table user comment '保存了所有的系统账号';

/*==============================================================*/
/* Table: user_menu                                             */
/*==============================================================*/
create table user_menu
(
   id                   bigint not null auto_increment,
   user_id              bigint,
   menu_id              bigint,
   primary key (id)
);

alter table user_menu comment '即用户拥有哪些菜单项';

/*==============================================================*/
/* Table: user_org_ref                                          */
/*==============================================================*/
create table user_org_ref
(
   id                   bigint not null auto_increment,
   user_id              bigint,
   org_id               bigint,
   primary key (id)
);

alter table user_org_ref comment '用于控制显示数据，即一个用户可以看到哪些组织里的数据。比如：一个分队管理员，只能看到该分队的队员';

/*==============================================================*/
/* Table: user_role_ref                                         */
/*==============================================================*/
create table user_role_ref
(
   id                   bigint not null auto_increment,
   user_id              bigint,
   role_id              bigint,
   primary key (id)
);

alter table event add constraint FK_Reference_12 foreign key (org_id)
      references organization (id) on delete restrict on update restrict;

alter table event add constraint FK_Reference_14 foreign key (law)
      references law (id) on delete restrict on update restrict;

alter table event add constraint FK_Reference_15 foreign key (term)
      references term (id) on delete restrict on update restrict;

alter table event add constraint FK_Reference_16 foreign key (crim_id)
      references crim (id) on delete restrict on update restrict;

alter table fuel_record add constraint FK_Reference_10 foreign key (car_id)
      references car (id) on delete restrict on update restrict;

alter table member add constraint FK_Reference_9 foreign key (org_id)
      references organization (id) on delete restrict on update restrict;

alter table mend_record add constraint FK_Reference_11 foreign key (car_id)
      references car (id) on delete restrict on update restrict;

alter table menu add constraint FK_Reference_19 foreign key (resource)
      references resource (id) on delete restrict on update restrict;

alter table role_resource_ref add constraint FK_Reference_1 foreign key (role_id)
      references role (id) on delete restrict on update restrict;

alter table role_resource_ref add constraint FK_Reference_2 foreign key (resource_id)
      references resource (id) on delete restrict on update restrict;

alter table term add constraint FK_Reference_13 foreign key (law_id)
      references law (id) on delete restrict on update restrict;

alter table user_menu add constraint FK_Reference_7 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_menu add constraint FK_Reference_8 foreign key (menu_id)
      references menu (id) on delete restrict on update restrict;

alter table user_org_ref add constraint FK_Reference_17 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_org_ref add constraint FK_Reference_18 foreign key (org_id)
      references organization (id) on delete restrict on update restrict;

alter table user_role_ref add constraint FK_Reference_3 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_role_ref add constraint FK_Reference_4 foreign key (role_id)
      references role (id) on delete restrict on update restrict;

