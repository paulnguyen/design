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

package ajia;

public abstract class Account {
    private float balance;
    private int accountNumber;

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void credit(float amount) {
        setBalance(getBalance() + amount);
    }

    public void debit(float amount) 
        throws InsufficientBalanceException {
        float balance = getBalance();
        if (balance < amount) {
            throw new InsufficientBalanceException(
                 "Total balance not sufficient");
        } else {
            setBalance(balance - amount);
        }
    }

    public float getBalance() {
        return this.balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
