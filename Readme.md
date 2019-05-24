## Mybatis-Helper
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Build Status](https://travis-ci.org/gaigeshen/mybatis-helper.svg?branch=develop)](https://travis-ci.org/gaigeshen/mybatis-helper)
[![Maven Central](https://img.shields.io/maven-central/v/me.gaigeshen.mybatis/mybatis-helper.svg)](http://mvnrepository.com/artifact/me.gaigeshen.mybatis/mybatis-helper)
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/https/oss.sonatype.org/me.gaigeshen.mybatis/mybatis-helper.svg)](https://oss.sonatype.org/content/repositories/snapshots/me/gaigeshen/mybatis/mybatis-helper)
[![GitHub last commit](https://img.shields.io/github/last-commit/gaigeshen/mybatis-helper.svg)](https://github.com/gaigeshen/mybatis-helper/commits)

### About entity

#### Base entity

All entities should extends `BaseEntity`, or no any data access methods for you.

#### Annotations

If you want to customize table name or column name, you can use `Table` and `Column` annotations

- Table

  You can define table name and table id column name by using this annotation

- Column

  You can define column name by using this annotation

### Methods of data access object

|     Method     |                         Description                          |
| :------------: | :----------------------------------------------------------: |
|    saveOne     |                       Save one entity                        |
|      save      |                      Save many entities                      |
|  saveOrUpdate  | Save or update entity, if the entity has id value, then update it |
|   deleteOne    |                     Delete by entity id                      |
|     delete     |                     Delete by conditions                     |
|    findOne     |                      Find entity by id                       |
|   findFirst    |               Find first record by conditions                |
|      find      |                      Find by conditions                      |
|     count      |             Returns records count by conditions              |
|    sliceup     |             Returns paged entities by conditions             |
|     exists     |           Check exists of entity id or conditions            |
|     update     |                     Update entity by id                      |
| updateNullable | Update entity by id, and null value properties update to null |

### How to configure without spring support

1. Configuration file `mybatis-config.xml`

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <configuration>
       <settings>
           <setting name="useGeneratedKeys" value="true"/>
       </settings>
       <environments default="development">
           <environment id="development">
               <transactionManager type="JDBC"/>
               <dataSource type="POOLED">
                   <property name="driver" value="com.mysql.jdbc.Driver"/>
                   <property name="url" value="Your database url"/>
                   <property name="username" value="Your database username"/>
                   <property name="password" value="Your database password"/>
               </dataSource>
           </environment>
       </environments>
       <mappers>
           <mapper class="me.gaigeshen.mybatis.helper.test.UserDao" />
       </mappers>
   </configuration>
   ```

2. Configure helper by using `MybatisHelperConfigurer` before mybatis `SqlSessionFactory` build

   ```java
   MybatisHelperConfigurer configurer = MybatisHelperConfigurer.create().configure();
   InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
   SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
   ```

3. Initialize result mappings by using mybatis `Configuration` object

   ```java
   configurer.initializeResultMappings(sqlSessionFactory.getConfiguration());
   ```

4. Now, you can use mybatis APIs

   ```java
   SqlSession session = sqlSessionFactory.openSession();
   UserDao userDao = session.getMapper(UserDao.class);
   ```

### How to configure with spring support

To be continue ...

