/**
 * This problem checks if input char is of type alphanumeric?
 * It returns true or false
 */

class IsAphanumeric {
  public static void main(String[] args) {
    System.out.println("IsAphanumeric Problem");
    System.out.println(IsAphanumeric('#'));
    System.out.println(IsAphanumeric('Z'));
    System.out.println(IsAphanumeric('9'));
  }

  private static boolean IsAphanumeric(char c) {
    if ((int) c >= (int) 'a' && (int) c <= (int) 'z') {
      return true;
    }

    if ((int) c >= (int) 'A' && (int) c <= (int) 'Z') {
      return true;
    }

    if ((int) c >= (int) '0' && (int) c <= (int) '9') {
      return true;
    }

    return false;
  }
}