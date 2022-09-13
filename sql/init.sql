# DROP DATABASE IF EXISTS irisz_blog1;

CREATE DATABASE irisz_blog CHARSET 'utf8mb4' COLLATE utf8mb4_general_ci;

CREATE USER irisz@'%' IDENTIFIED BY 'mmm0903.@';

GRANT ALL PRIVILEGES ON irisz_blog.* TO irisz@'%';

FLUSH PRIVILEGES ;

USE irisz_blog;


/* --------------------------------------------------------- */
/**
  用户表
 */
CREATE TABLE sys_account
(
    id  INTEGER  NOT NULL PRIMARY KEY AUTO_INCREMENT comment '用户ID',
    username     VARCHAR(32) NOT NULL UNIQUE COMMENT '用户名',
    password     VARCHAR(256) NOT NULL COMMENT '密码',
    display_name VARCHAR(32) NOT NULL COMMENT '前端显示名字',
    email        VARCHAR(64) NOT NULL COMMENT '邮箱',
    is_admin     TINYINT(1) DEFAULT 1 NOT NULL COMMENT '是否管理员',
    is_active    TINYINT(1) DEFAULT 1 NOT NULL COMMENT '用户是否激活',
    date_joined  DATETIME NOT NULL COMMENT '用户加入时间',
    avatar       VARCHAR(64) COMMENT '头像地址' ,
    last_login   DATETIME COMMENT '上次登录时间',
    create_time  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete    Tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否逻辑删除'
) COMMENT '用户表';

/* --------------------------------------------------------- */


/* --------------------------------------------------------- */
/**
  博客文章表
 */
CREATE TABLE blog_article
(
    id         INTEGER     NOT NULL AUTO_INCREMENT COMMENT '文章ID',
    title      VARCHAR(32) NOT NULL COMMENT '文章标题',
    content    TEXT        NOT NULL COMMENT '文章内容',
    read_count BIGINT      NOT NULL DEFAULT 0 COMMENT '阅读量',
    status     TINYINT     NOT NULL COMMENT '文章状态',
    author_id  INTEGER     NOT NULL COMMENT '文章作者ID',
    category_id  INTEGER NOT NULL COMMENT '文章分类ID',
    language_id INTEGER NOT NULL COMMENT '文章所属开发语言',
    create_time  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete    Tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否逻辑删除',
    PRIMARY KEY(id),
    FOREIGN KEY (author_id) REFERENCES sys_account(id),
    FOREIGN KEY (category_id) REFERENCES blog_category(id)
) COMMENT '博客文章表';

/* --------------------------------------------------------- */


/* --------------------------------------------------------- */
/**
  博客分类表
 */
CREATE TABLE blog_category
(
    id              INTEGER NOT NULL AUTO_INCREMENT COMMENT '文章分类ID',
    name            VARCHAR(32)     NOT NULL    COMMENT '分类值',
    display_name    VARCHAR(128)    NOT NULL    COMMENT '分类名称',
    create_time  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete    Tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否逻辑删除',
    PRIMARY KEY (id)
) COMMENT '博客分类表';

/* --------------------------------------------------------- */


/* --------------------------------------------------------- */
/**
  博客标签表
 */
CREATE TABLE blog_tag
(
    id              INTEGER NOT NULL AUTO_INCREMENT COMMENT '文章标签ID',
    name            VARCHAR(32) NOT NULL    COMMENT '文章标签值',
    display_name    VARCHAR(128) NOT NULL   COMMENT '文章标签名称',
    color           VARCHAR(32) NOT NULL    COMMENT '文章标签颜色',
    create_time  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete    Tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否逻辑删除',
    PRIMARY KEY (id)
) COMMENT '博客标签表';
/* --------------------------------------------------------- */


/* --------------------------------------------------------- */
/**
  文章标签关联表
 */
CREATE TABLE blog_article_tag
(
    id          INTEGER NOT NULL AUTO_INCREMENT COMMENT '文章管理标签ID',
    article_id  INTEGER NOT NULL    COMMENT '文章ID',
    tag_id      INTEGER NOT NULL    COMMENT '标签ID',
    create_time  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete    Tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否逻辑删除',
    PRIMARY KEY (id),
    FOREIGN KEY (article_id) REFERENCES blog_article(id),
    FOREIGN KEY (tag_id) REFERENCES blog_tag(id)
) COMMENT '文章标签关联表';
/* --------------------------------------------------------- */





/* --------------------------------------------------------- */
/**
  文章开发语言类型
 */
CREATE TABLE blog_article_language
(
    id          INTEGER NOT NULL AUTO_INCREMENT COMMENT '开发语言记录ID',
    `language`  VARCHAR(32) NOT NULL    COMMENT '文章开发语言类型',
    create_time  DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    is_delete    Tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否逻辑删除',
    PRIMARY KEY (id)
) COMMENT '文章开发语言类型表';
/* --------------------------------------------------------- */
