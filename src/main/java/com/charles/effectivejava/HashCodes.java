package com.charles.effectivejava;

public class HashCodes {

	public static void main(String [] args){
		
		HashCodes h = new HashCodes();
		
		System.out.println(h.hashCode());
		
		Foo f = h.new Foo();
		System.out.println(f.hashCode());
		Foo f2 = h.new Foo();
		System.out.println(f2.hashCode());
	}
	
	public class Foo{
		String name;
		Integer age;
		
		public Foo(){
			name = "charles";
			age = 50;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((age == null) ? 0 : age.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Foo other = (Foo) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (age == null) {
				if (other.age != null)
					return false;
			} else if (!age.equals(other.age))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		private HashCodes getOuterType() {
			return HashCodes.this;
		}
	}
}
