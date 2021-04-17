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
import org.springframework.samples.jpetstore.dao.AccountDao;
import org.springframework.samples.jpetstore.domain.Account;

/**
 * In this and other DAOs in this package, a DataSource property
 * is inherited from the SqlMapClientDaoSupport convenience superclass
 * supplied by Spring. DAOs don't need to extend such superclasses,
 * but it saves coding in many cases. There are analogous superclasses
 * for JDBC (JdbcDaoSupport), Hibernate (HibernateDaoSupport),
 * JDO (JdoDaoSupport) etc.
 *
 * <p>This and other DAOs are configured using Dependency Injection.
 * This means, for example, that Spring can source the DataSource
 * from a local class, such as the Commons DBCP BasicDataSource,
 * or from JNDI, concealing the JNDI lookup from application code.
 * 
 * @author Juergen Hoeller
 * @author Colin Sampaleanu
 */
public class SqlMapAccountDao extends SqlMapClientDaoSupport implements AccountDao {

  public Account getAccount(String username) throws DataAccessException {
    return (Account) getSqlMapClientTemplate().queryForObject("getAccountByUsername", username);
  }

  public Account getAccount(String username, String password) throws DataAccessException {
    Account account = new Account();
    account.setUsername(username);
    account.setPassword(password);
    return (Account) getSqlMapClientTemplate().queryForObject("getAccountByUsernameAndPassword", account);
  }

  public void insertAccount(Account account) throws DataAccessException {
    getSqlMapClientTemplate().insert("insertAccount", account);
    getSqlMapClientTemplate().insert("insertProfile", account);
    getSqlMapClientTemplate().insert("insertSignon", account);
  }

  public void updateAccount(Account account) throws DataAccessException {
    getSqlMapClientTemplate().update("updateAccount", account, 1);
    getSqlMapClientTemplate().update("updateProfile", account, 1);
    if (account.getPassword() != null && account.getPassword().length() > 0) {
      getSqlMapClientTemplate().update("updateSignon", account, 1);
    }
  }
 
	public List getUsernameList() throws DataAccessException {
		return getSqlMapClientTemplate().queryForList("getUsernameList", null);
	}

}
