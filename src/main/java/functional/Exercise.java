package functional;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

public class Exercise {

    /**
     * http://www.deadcoderising.com/2015-09-07-java-8-functional-composition-
     * using-compose-and- andthen/
     */
    // Function
    @Test
    public void functionTest() {
	Function<Integer, Integer> times2 = e -> e * 2;
	Function<Integer, Integer> squared = e -> e * e;

	// Squared first then times two
	Assert.assertEquals(Integer.valueOf(32), times2.compose(squared).apply(4));

	// Times two first then squared
	Assert.assertEquals(Integer.valueOf(64), times2.andThen(squared).apply(4));

	Function<Integer, Integer> foo = e -> e * e;
	Function<Integer, String> bar = e -> e + " bar";

	Assert.assertEquals("25 bar", foo.andThen(bar).apply(5));
    }

    // BiFunction
    @Test
    public void bifunc() {
	{
	    // BiFunction takes in two arguments
	    BiFunction<String, String, String> bifunc = (s1, s2) -> {
		String s3 = s1 + s2;
		return s3;
	    };
	    assertEquals("foobar", bifunc.apply("foo", "bar"));
	}
	{
	    class Article {
		String title;
		String author;
		String tag;
		Date published = new Date();

		public Article(String title, String author, String tag) {
		    this.title = title;
		    this.author = author;
		    this.tag = tag;
		}

		public String getTitle() {
		    return title;
		}

		public String getAuthor() {
		    return author;
		}

		public String getTag() {
		    return tag;
		}

		public Date getPublished() {
		    return published;
		}
	    }

	    // Find articles by author
	    BiFunction<String, List<Article>, List<Article>> byAuthor = (name, articles) -> articles.stream()
		    .filter(a -> a.getAuthor().equals(name)).collect(Collectors.toList());

	    // Find articles by tag
	    BiFunction<String, List<Article>, List<Article>> byTag = (tagName, articles) -> articles.stream()
		    .filter(a -> a.getTag().equals(tagName)).collect(Collectors.toList());

	    // BiFunction takes two arguments and outputs one return value.
	    // Thus, there isn't a compose function because single return value
	    // doesn't map to two
	    // parameter BiFunctions

	    Function<List<Article>, List<Article>> sortByDate = articles -> articles.stream()
		    .sorted((x, y) -> y.getPublished().compareTo(x.getPublished())).collect(Collectors.toList());

	    Function<List<Article>, Optional<Article>> first = a -> a.stream().findFirst();

	    Function<List<Article>, Optional<Article>> newest = first.compose(sortByDate);

	    BiFunction<String, List<Article>, Optional<Article>> newestByAuthor = byAuthor.andThen(newest);
	    BiFunction<String, List<Article>, List<Article>> byAuthorSorted = byAuthor.andThen(sortByDate);
	    BiFunction<String, List<Article>, Optional<Article>> newestByTag = byTag.andThen(newest);
	}
    }

    @Test
    public void bipredicateTest() {
	BiPredicate<Integer, String> condition = (i, s) -> i > 20 && s.startsWith("R");
	BiPredicate<Integer, String> cond2 = condition.and((a, b) -> a < 50 && b.contains("lol"));

	Assert.assertTrue(condition.test(21, "R"));
	Assert.assertTrue(cond2.test(30, "Rlol"));
    }

    @Test
    public void biconsumerTest() {
	Map<String, Integer> map = new HashMap<>();
	map.put("a", 1);
	map.put("b", 2);
	map.put("c", 3);
	BiConsumer<String, Integer> bicons = (key, value) -> System.out
		.println(String.format("Key: %s Value: %d", key, value));
	map.forEach(bicons);
    }

    public static void p(Object o) {
	System.out.println(o.toString());
    }
}
