package com.anim.FullStack.SpringBack.Service;

import com.anim.FullStack.SpringBack.Dto.Purchase;
import com.anim.FullStack.SpringBack.Dto.PurchaseResponse;

public interface CheckoutService {
	
	PurchaseResponse placeOrder(Purchase purchase);
}
