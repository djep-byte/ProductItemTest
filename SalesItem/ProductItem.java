import java.util.ArrayList;
import java.util.Iterator;

public class ProductItem {
    private String title;
    private int costInCents;
    private ArrayList<Review> reviews;

    public ProductItem(String title, int costInCents) {
        this.title = title;
        this.costInCents = costInCents;
        this.reviews = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public int getCostInCents() {
        return costInCents;
    }

    public int getReviewCount() {
        return reviews.size();
    }

    public boolean addReview(String reviewer, String content, int stars) {
        if (isInvalidRating(stars)) {
            return false;
        }
        if (findReviewByReviewer(reviewer) != null) {
            return false;
        }
        reviews.add(new Review(reviewer, content, stars));
        return true;
    }

    public void deleteReview(int index) {
        if (index >= 0 && index < reviews.size()) {
            reviews.remove(index);
        }
    }

    public void upvoteReview(int index) {
        if (index >= 0 && index < reviews.size()) {
            reviews.get(index).upvote();
        }
    }

    public void downvoteReview(int index) {
        if (index >= 0 && index < reviews.size()) {
            reviews.get(index).downvote();
        }
    }

    public void displayDetails() {
        System.out.println("*** " + title + " ***");
        System.out.println("Price: " + formatPrice(costInCents));
        System.out.println();
        System.out.println("Customer reviews:");
        for (Review review : reviews) {
            System.out.println("-----------------------------------");
            System.out.println(review.getDetailString());
        }
        System.out.println();
        System.out.println("=====================================");
    }

    public Review getMostHelpfulReview() {
        if (reviews.isEmpty()) {
            return null;
        }

        Iterator<Review> iterator = reviews.iterator();
        Review top = iterator.next();
        while (iterator.hasNext()) {
            Review current = iterator.next();
            if (current.getHelpfulVotes() > top.getHelpfulVotes()) {
                top = current;
            }
        }
        return top;
    }

    private boolean isInvalidRating(int stars) {
        return stars < 0 || stars > 5;
    }

    private Review findReviewByReviewer(String reviewer) {
        for (Review r : reviews) {
            if (r.getReviewer().equals(reviewer)) {
                return r;
            }
        }
        return null;
    }

    private String formatPrice(int cents) {
        int dollars = cents / 100;
        int remainingCents = cents % 100;
        return String.format("$%d.%02d", dollars, remainingCents);
    }
}