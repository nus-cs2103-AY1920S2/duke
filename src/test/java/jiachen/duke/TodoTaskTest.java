package jiachen.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTaskTest {

  @Test
  void testInvalidConstructor() {
    Assertions.assertThrows(
            InvalidDukeFormatException.class,
            () -> {
              new TodoTask("");
            });
  }

  @Test
  void testConstructor() {
    Assertions.assertDoesNotThrow(
            () -> {
              new TodoTask("this is a discription");
            });
  }

  @Test
  void testToString() {
    try {
      TodoTask todo = new TodoTask("hellololooloo there");
      assertEquals(todo.toString(), "[T][✘] hellololooloo there");
    } catch (InvalidDukeFormatException e) {
      e.printStackTrace();
    }
  }

  @Test
  void format() {
    try {
      TodoTask todo = new TodoTask("hellololooloo there");
      assertEquals(todo.format(), "T | 0 | hellololooloo there");
    } catch (InvalidDukeFormatException e) {
      e.printStackTrace();
    }
  }
}
