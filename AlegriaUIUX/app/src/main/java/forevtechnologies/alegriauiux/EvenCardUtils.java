package forevtechnologies.alegriauiux;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import forevtechnologies.alegriauiux.models.CategoryCardModel;

public class EvenCardUtils {

    public static Collection<CategoryCardModel> generateCategoryCards() {
        List<CategoryCardModel> categoryCardModels = new ArrayList<>(8);

        {
            categoryCardModels.add(CategoryCardModel
                    .newBuilder()
                    .withCategoryTitle("Informal")
                    .withCategorySubtitle("Lorep Ipsum")
                    .withCategoryRound("Preliminaries")
                    .withImageResId(R.drawable.arif)
                    .withBackgroundColorResId(R.color.indigo)
                    .build());

        }

        {
            categoryCardModels.add(CategoryCardModel
                    .newBuilder()
                    .withCategoryTitle("Performing Arts")
                    .withCategorySubtitle("Lorep Ipsum")
                    .withCategoryRound("Qualification")
                    .withImageResId(R.drawable.pratik)
                    .withBackgroundColorResId(R.color.pink)
                    .build());

        }

        {
            categoryCardModels.add(CategoryCardModel
                    .newBuilder()
                    .withCategoryTitle("Literary Arts")
                    .withCategorySubtitle("Lorep Ipsum")
                    .withCategoryRound("Heats")
                    .withImageResId(R.drawable.shrey)
                    .withBackgroundColorResId(R.color.yellow)
                    .build());

        }

        {
            categoryCardModels.add(CategoryCardModel
                    .newBuilder()
                    .withCategoryTitle("Management")
                    .withCategorySubtitle("Lorep Ipsum")
                    .withCategoryRound("Round of 16")
                    .withImageResId(R.drawable.shubhang)
                    .withBackgroundColorResId(R.color.orange)
                    .build());

        }

        {
            categoryCardModels.add(CategoryCardModel
                    .newBuilder()
                    .withCategoryTitle("Sports & Gaming")
                    .withCategorySubtitle("Lorep Ipsum")
                    .withCategoryRound("Group A")
                    .withImageResId(R.drawable.sooraj)
                    .withBackgroundColorResId(R.color.purple)
                    .build());

        }

        {
            categoryCardModels.add(CategoryCardModel
                    .newBuilder()
                    .withCategoryTitle("Fine Arts")
                    .withCategorySubtitle("Lorep Ipsum")
                    .withCategoryRound("Group A")
                    .withImageResId(R.drawable.roshan)
                    .withBackgroundColorResId(R.color.blue)
                    .build());

        }

        {
            categoryCardModels.add(CategoryCardModel
                    .newBuilder()
                    .withCategoryTitle("Technical Events")
                    .withCategorySubtitle("Lorep Ipsum")
                    .withCategoryRound("Group A")
                    .withImageResId(R.drawable.arif)
                    .withBackgroundColorResId(R.color.teal)
                    .build());

        }
        {
            categoryCardModels.add(CategoryCardModel
                    .newBuilder()
                    .withCategoryTitle("Workshops")
                    .withCategorySubtitle("Lorep Ipsum")
                    .withCategoryRound("Group A")
                    .withImageResId(R.drawable.roshan)
                    .withBackgroundColorResId(R.color.red)
                    .build());

        }
        return categoryCardModels;
    }
}
