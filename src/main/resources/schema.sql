DROP TABLE IF EXISTS MEMBER;
CREATE TABLE MEMBER(
  member_id bigint not null primary key auto_increment,
  name varchar(100) not null
);

DROP TABLE IF EXISTS api_auth_group;
CREATE TABLE api_auth_group(
  group_id bigint not null primary key auto_increment,
  name varchar(100) not null
);

DROP TABLE IF EXISTS api_info;
CREATE TABLE api_info(
    api_id varchar(100) not null primary key
);

DROP TABLE IF EXISTS api_group;
CREATE TABLE api_group(
    group_id bigint not null,
    api_id varchar(100) not null
);

INSERT INTO api_auth_group(group_id, name) values (1, 'test1');
INSERT INTO api_info(api_id) values ('TEST');
INSERT INTO api_group(group_id, api_id) values (1, 'TEST');
