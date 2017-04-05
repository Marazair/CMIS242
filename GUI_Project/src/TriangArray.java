class TriangArray  { 
   static public  String [] getTriangArray(int num) {
      int[][] arr = new int[num][]; 
      String[] allRows = new String[arr.length];
      for(int row = 0; row < arr.length; row++)
         arr[row] = new int[row + 1]; 
      for(int row = 0; row < arr.length; row++) {
         allRows[row] = "";
         for(int col = 0; col < arr[row].length; col++) { 
            arr[row][col] = (row + 1) * (col + 1);
            allRows[row] += arr[row][col] + " ";
         }      
      }
      return allRows;
   }
}
