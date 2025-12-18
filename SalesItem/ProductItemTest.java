import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductItemTest {
    private ProductItem product;

    @BeforeEach
    public void setUp() {
        product = new ProductItem("Buku Resep Masakan", 2499);
    }

    @Test
    public void testConstructor() {
        assertEquals("Buku Resep Masakan", product.getTitle());
        assertEquals(2499, product.getCostInCents());
        assertEquals(0, product.getReviewCount());
    }

    @Test
    public void testAddValidReview() {
        boolean success = product.addReview("Budi", "Resepnya mudah diikuti!", 5);
        assertTrue(success);
        assertEquals(1, product.getReviewCount());
    }

    @Test
    public void testAddInvalidRatingTooHigh() {
        boolean success = product.addReview("Citra", "Terlalu mahal", 6);
        assertFalse(success);
        assertEquals(0, product.getReviewCount());
    }

    @Test
    public void testAddInvalidRatingTooLow() {
        boolean success = product.addReview("Citra", "Rating aneh", -1);
        assertFalse(success);
        assertEquals(0, product.getReviewCount());
    }

    @Test
    public void testAddDuplicateReviewer() {
        product.addReview("Dian", "Bagus!", 4);
        boolean success = product.addReview("Dian", "Lupa nambahin, fotonya juga bagus!", 5);
        assertFalse(success);
        assertEquals(1, product.getReviewCount());
    }

    @Test
    public void testVotingMechanism() {
        product.addReview("Eka", "Lumayan", 3);
        product.upvoteReview(0);
        product.upvoteReview(0);
        product.downvoteReview(0);
        Review mostHelpful = product.getMostHelpfulReview();
        assertEquals(1, mostHelpful.getHelpfulVotes());
    }

    @Test
    public void testMostHelpfulReviewSelection() {
        product.addReview("Budi", "Fotonya jelek", 1);
        product.addReview("Citra", "Cukup membantu", 3);
        product.addReview("Dian", "Resep andalan!", 5);

        product.upvoteReview(2);
        product.upvoteReview(2);
        product.upvoteReview(1);
        product.downvoteReview(0);

        Review best = product.getMostHelpfulReview();
        assertEquals("Dian", best.getReviewer());
        assertEquals(2, best.getHelpfulVotes());
    }

    @Test
    public void testMostHelpfulWhenNoReviews() {
        Review best = product.getMostHelpfulReview();
        assertNull(best);
    }

    @Test
    public void testRemoveReview() {
        product.addReview("Budi", "Komentar 1", 5);
        product.addReview("Citra", "Komentar 2", 4);
        assertEquals(2, product.getReviewCount());

        product.deleteReview(0);
        assertEquals(1, product.getReviewCount());

        Review remaining = product.getMostHelpfulReview();
        assertEquals("Citra", remaining.getReviewer());
    }

    @Test
    public void testRemoveInvalidIndex() {
        product.addReview("Budi", "Komentar 1", 5);
        product.deleteReview(99);
        product.deleteReview(-1);
        assertEquals(1, product.getReviewCount());
    }
}