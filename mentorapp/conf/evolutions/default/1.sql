# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        bigint not null,
  user_name                 varchar(255),
  password                  varchar(255),
  display_name              varchar(255),
  current_employer          varchar(255),
  current_title             varchar(255),
  work_experience           varchar(255),
  mentors                   varchar(255),
  mentees                   varchar(255),
  is_mentor                 boolean,
  is_mentee                 boolean,
  constraint pk_user primary key (id))
;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_seq;

