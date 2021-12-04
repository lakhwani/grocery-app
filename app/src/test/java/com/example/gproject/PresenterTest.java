package com.example.gproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class PresenterTest {
    @Mock
    MainActivity view;

    @Mock
    DBModel model;

    @Test
    public void test_presenter_checkLoginCredentials1() {

        MyPresenter presenter = new MyPresenter(model, view);
        presenter.checkLoginCredentials("", "");

        verify(view).displayMessage("username cannot be empty");
    }

    @Test
    public void test_presenter_checkLoginCredentials2() {

        MyPresenter presenter = new MyPresenter(model, view);
        presenter.checkLoginCredentials("peter", "p");

        verify(model, times(1)).checkValidUserCredentials("peter", "p", presenter);
    }

    @Test
    public void test_presenter_checkLoginCredentials3() {

        MyPresenter presenter = new MyPresenter(model, view);
        presenter.checkLoginCredentials("peter", "");

        verify(model, times(1)).checkValidUserCredentials("peter", "", presenter);
    }

    @Test
    public void test_presenter_onValidCredentialsO() {

        MyPresenter presenter = new MyPresenter(model, view);
        Customer c = new Customer("peter@gmail.com", "1234", "peter@gmail.com", 1, "Peter", "Parker");

        presenter.onValidCredentials(c);

        verify(view).displayMessage("VALID login! peter@gmail.com");
        verify(view, times(1)).goToCustomerMain(c);
    }

    @Test
    public void test_presenter_onValidCredentialsC() {

        MyPresenter presenter = new MyPresenter(model, view);
        Owner o = new Owner("peter@gmail.com", "1234", "peter@gmail.com", 1, "Peter", "Parker");

        presenter.onValidCredentials(o);

        verify(view).displayMessage("VALID login! peter@gmail.com");
        verify(view, times(1)).goToOwnerMain(o);
    }

    @Test
    public void test_presenter_onInvalidCredentials() {

        MyPresenter presenter = new MyPresenter(model, view);

        presenter.onInvalidCredentials(anyString());

        verify(view).displayMessage("Invalid login! " + anyString());

    }
}
