package com.gamificacion.demo.DTO;

import java.io.Serializable;

public class CountDTO  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public CountDTO() {
		
	}

	private long count;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
}
