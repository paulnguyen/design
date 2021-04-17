/*
Copyright 2009 Ramnivas Laddad

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at 
    http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and 
limitations under the License. 
*/

package org.springframework.samples.jpetstore.dao.ibatis;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.samples.jpetstore.dao.CategoryDao;
import org.springframework.samples.jpetstore.domain.Category;

public class SqlMapCategoryDao extends SqlMapClientDaoSupport implements CategoryDao {

  public List getCategoryList() throws DataAccessException {
    return getSqlMapClientTemplate().queryForList("getCategoryList", null);
  }

  public Category getCategory(String categoryId) throws DataAccessException {
    return (Category) getSqlMapClientTemplate().queryForObject("getCategory", categoryId);
  }

}
