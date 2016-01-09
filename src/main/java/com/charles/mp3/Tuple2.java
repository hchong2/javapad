package com.charles.mp3;


public class Tuple2<T1, T2>
{
   private final T1 val1;
   private final T2 val2;

   public Tuple2(final T1 val1, final T2 val2)
   {
      this.val1 = val1;
      this.val2 = val2;
   }

   public static <T1, T2> Tuple2<T1, T2> tuple(final T1 val1, final T2 val2)
   {
      return new Tuple2<T1, T2>(val1, val2);
   }

   public T1 getFirst()
   {
      return this.val1;
   }

   public T2 getSecond()
   {
      return this.val2;
   }

   @Override
   public boolean equals(final Object obj)
   {
      if (this == obj)
         return true;

      if (obj == null || getClass() != obj.getClass())
         return false;

      final Tuple2<?, ?> other = (Tuple2<?, ?>) obj;
      if (this.val1 == null)
      {
         if (other.val1 != null)
            return false;
      }
      else if (!this.val1.equals(other.val1))
         return false;

      if (this.val2 == null)
      {
         if (other.val2 != null)
            return false;
      }
      else if (!this.val2.equals(other.val2))
         return false;

      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;

      result = prime * result + ((this.val1 == null) ? 0 : this.val1.hashCode());
      result = prime * result + ((this.val2 == null) ? 0 : this.val2.hashCode());

      return result;
   }

   @Override
   public String toString()
   {
      return "(" + this.val1 + ", " + this.val2 + ")";
   }
}
