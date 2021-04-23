package com.bank.sys.modals;

import com.bank.sys.models.User;

public class UserModalEntry {
    public User user;

    public UserModalEntry(User user) {
        this.user = user;
    }

    public String toString() {
        return user.getName() + " " + user.getSurname() + " " + user.getPesel();
    }
}
