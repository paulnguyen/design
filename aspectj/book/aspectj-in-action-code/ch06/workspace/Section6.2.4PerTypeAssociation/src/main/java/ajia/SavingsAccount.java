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

//Listing 6.6 Modification to illustrate pertypewithin() association
package ajia;

public class SavingsAccount extends Account {
	private static final float MINIMUM_BALANCE = 25.0f;

	public SavingsAccount(int accountNumber) {
		super(accountNumber);
	}

	public void debit(float amount) throws InsufficientBalanceException {
		if (getBalance() < (amount + MINIMUM_BALANCE)) {
			throw new InsufficientBalanceException(
					"Minimum balance not maintained");
		}
		super.debit(amount);
	}
}