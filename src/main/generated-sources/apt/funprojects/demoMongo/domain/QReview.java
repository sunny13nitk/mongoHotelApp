package funprojects.demoMongo.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QReview extends BeanPath<Review> {

    private static final long serialVersionUID = -646301296L;

    public static final QReview review = new QReview("review");

    public final BooleanPath approved = createBoolean("approved");

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final StringPath userName = createString("userName");

    public QReview(String variable) {
        super(Review.class, forVariable(variable));
    }

    public QReview(Path<? extends Review> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReview(PathMetadata metadata) {
        super(Review.class, metadata);
    }

}

