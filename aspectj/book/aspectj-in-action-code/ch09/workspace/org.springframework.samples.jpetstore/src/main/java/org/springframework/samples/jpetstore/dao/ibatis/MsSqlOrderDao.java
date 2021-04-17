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
import org.springframework.samples.jpetstore.domain.LineItem;
import org.springframework.samples.jpetstore.domain.Order;

public class MsSqlOrderDao extends SqlMapOrderDao {

  /**
   * Special MS SQL Server version to allow the Item ID
	 * to be retrieved from an identity column.
   */
  public void insertOrder(Order order) throws DataAccessException {
    Integer orderId = (Integer) getSqlMapClientTemplate().queryForObject("msSqlServerInsertOrder", order);
    order.setOrderId(orderId.intValue());
    getSqlMapClientTemplate().insert("insertOrderStatus", order);
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = (LineItem) order.getLineItems().get(i);
      lineItem.setOrderId(order.getOrderId());
      getSqlMapClientTemplate().insert("insertLineItem", lineItem);
    }
  }
  
}
