package com.interfaces.models;

public interface UserOperations<U,P> {
	U login();
	U register();
	String buyProduct(P pro);
	String removeProduct();
	String checkPoint();
}
