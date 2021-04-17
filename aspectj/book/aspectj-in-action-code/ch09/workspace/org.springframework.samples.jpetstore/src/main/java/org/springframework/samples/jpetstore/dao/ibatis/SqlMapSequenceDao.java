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

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SqlMapSequenceDao extends SqlMapClientDaoSupport {

  /**
   * This is a generic sequence ID generator that is based on a database
   * table called 'SEQUENCE', which contains two columns (NAME, NEXTID).
   * This approach should work with any database.
   * @param name the name of the sequence
   * @return the next ID
   */
  public int getNextId(String name) throws DataAccessException {
    Sequence sequence = new Sequence(name, -1);
    sequence = (Sequence) getSqlMapClientTemplate().queryForObject("getSequence", sequence);
    if (sequence == null) {
      throw new DataRetrievalFailureException(
					"Could not get next value of sequence '" + name + "': sequence does not exist");
    }
    Object parameterObject = new Sequence(name, sequence.getNextId() + 1);
    getSqlMapClientTemplate().update("updateSequence", parameterObject, 1);
    return sequence.getNextId();
  }

}
