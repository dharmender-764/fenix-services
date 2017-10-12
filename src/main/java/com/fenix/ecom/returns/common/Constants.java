package com.fenix.ecom.returns.common;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static Map<Integer, String> RETURN_REASONS;
	static {
		RETURN_REASONS = new HashMap<>();
		RETURN_REASONS.put(1, "Damaged Product");
		RETURN_REASONS.put(2, "Missing Parts/Accessories");
		RETURN_REASONS.put(3, "No longer needed");
		RETURN_REASONS.put(4, "Got better deal");
		RETURN_REASONS.put(5, "Item arrived too late");
		RETURN_REASONS.put(6, "Wrong item");
		RETURN_REASONS.put(7, "Inaccurate description");
		RETURN_REASONS.put(8, "Not as expected");
	}
}
