package expeditors.week06.functional.lambdas;

public class Client {
    enum Rating {
        EXCELLENT,
        GOOD,
        AVERAGE,
        POOR;

        @Override
        public String toString() {
            return name().substring(0,1).toUpperCase() +
                    name().substring(1).toLowerCase();
        }
    }

    public static void main(String[] args) {
        Rater<Double, String> ratingEvaluatorAsText = rating -> {
            return switch (rating) {
                case Double r when r >= 4.5 -> "Excellent";
                case Double r when r >= 3.5 -> "Good";
                case Double r when r >= 2.0 -> "Average";
                case Double r when r >= 0.0 -> "Poor";
                default -> "Unknown";
            };
        };

        Rater<Integer, Rating> ratingEvaluatorAsEnum = rating -> {
            if (rating >= 4) {
                return Rating.EXCELLENT;
            } else if (rating == 3) {
                return Rating.GOOD;
            } else if (rating == 2) {
                return Rating.AVERAGE;
            } else {
                return Rating.POOR;
            }
        };

        Rater<String, String> optimisticRatingEvaluator = input ->
                String.format("%s - Excellent!!", input);

        System.out.println("Using Evaluator as Text:");
        System.out.println(ratingEvaluatorAsText.rate(4.75));
        System.out.println(ratingEvaluatorAsText.rate(3.7));
        System.out.println(ratingEvaluatorAsText.rate(2.01));
        System.out.println(ratingEvaluatorAsText.rate(.5));
        System.out.println(ratingEvaluatorAsText.rate(-1.0));
        System.out.println();
        System.out.println("Using Evaluator as Enum:");
        System.out.println(ratingEvaluatorAsEnum.rate(5));
        System.out.println(ratingEvaluatorAsEnum.rate(4));
        System.out.println(ratingEvaluatorAsEnum.rate(3));
        System.out.println(ratingEvaluatorAsEnum.rate(2));
        System.out.println(ratingEvaluatorAsEnum.rate(1));
        System.out.println(ratingEvaluatorAsEnum.rate(0));
        System.out.println();
        System.out.println("Using Optimistic Evaluator:");
        System.out.println(optimisticRatingEvaluator.rate("4.0"));
        System.out.println(optimisticRatingEvaluator.rate("3.0"));
        System.out.println(optimisticRatingEvaluator.rate(null));
    }
}
