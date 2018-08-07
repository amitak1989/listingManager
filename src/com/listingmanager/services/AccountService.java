package com.listingmanager.services;

import com.google.api.services.mybusiness.v4.MyBusiness;
import com.google.api.services.mybusiness.v4.model.Account;
import com.google.api.services.mybusiness.v4.model.ListAccountsResponse;
import com.listingmanager.util.MyBusinessFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class AccountService {
    public static List<Account> getAccountList()throws GeneralSecurityException, IOException {
        MyBusiness.Accounts.List accountsList = MyBusinessFactory.getBusinessObject().accounts().list();
        ListAccountsResponse response = accountsList.execute();
        List<Account> accounts = response.getAccounts();

        for (Account account : accounts) {
            System.out.println(account.toPrettyString());
        }
        return accounts;
    }


    /**
     * Demonstrates getting an account by name.
     * @param accountName The name (resource path) of the account to retrieve.
     * @returns Account The requested account.
     */
    public static Account getAccount(String accountName) throws Exception {
        MyBusiness.Accounts.Get account =
                MyBusinessFactory.getBusinessObject().accounts().get(accountName);
        Account response = account.execute();

        return response;
    }

    /**
     * Updates a field for a business account.
     * @param accountName The name (resource path) of the account to update.
     * @param newAccountName A string containing the updated value.
     * @throws IOException
     */
    public static void updateAccount(String accountName, String newAccountName) throws GeneralSecurityException,IOException {
        Account updateAccount = new Account();
        updateAccount.setName(accountName);
        updateAccount.set("accountName", newAccountName);

        MyBusiness.Accounts.Update accountUpdate =
                MyBusinessFactory.getBusinessObject().accounts().update(accountName, updateAccount);

        //accountUpdate.setLanguageCode("en");
        Account updatedAccount  = accountUpdate.execute();

        System.out.printf("Updated account:\n%s", updatedAccount.toPrettyString());
    }
}
