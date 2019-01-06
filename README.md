InvertedIndex - Amazon Review and Q&A Search
===========================================

In this project, I have implemented a program to allow searching of a database of Amazon reviews and Q&A content. I uses the following concepts:

- Efficient data structures
- JSON parsing
- Command line input/output
- Object-oriented design

## Requirements

### Setup

Download the [5-core Cell Phones and Accessories review](http://snap.stanford.edu/data/amazon/productGraph/categoryFiles/reviews_Cell_Phones_and_Accessories_5.json.gz) dataset and the [Cell Phones and Accessories Q&A](http://jmcauley.ucsd.edu/data/amazon/qa/qa_Cell_Phones_and_Accessories.json.gz) dataset from [Julian McAuley, UCSD](http://jmcauley.ucsd.edu/data/amazon/). Unzip the files and put the json source in your top-level project directory. These are large datasets and thus it was required to think about efficiency!

### InvertedIndex

I have created an `InvertedIndex` data structure and used *two* instances of it. The first holds the data from the review dataset described above, and the second holds the data from the Q&A dataset described above. 

An [inverted index](https://en.wikipedia.org/wiki/Inverted_index) (also see [this page](https://nlp.stanford.edu/IR-book/html/htmledition/a-first-take-at-building-an-inverted-index-1.html)) is a common data structure used for searching documents. It maps terms to the documents where those terms appear. This makes it easy to search for a term and get back a list of documents that contain the word.

I have carefully designed my `InvertedIndex` so that it can be easily be used for both the review and the Q&A data.

### Indexing 

The goal of the `InvertedIndex` is to support searching of text. For the review dataset I am indexing the text in the field `reviewText`. For the Q&A dataset I am indexing both the `question` and `answer` fields. This allows me to search for a term and get a list of reviews where the review text contains the term and a list of Q&A results where the question or answer contains the term.

The terms that I have used are alphanumeric characters (letters or digits) and is case insensitive.

Suppose that we have two documents:

```
doc1 - "The cat and the dog ran."

doc2 - "Dog1 and dog2 ran the course."
```

the inverted index would look as follows:

```
the -> doc1, doc2
cat -> doc1
and -> doc1, doc2
dog -> doc1
ran -> doc1, doc2
dog1 -> doc2
dog2 -> doc2
course -> doc2
```

Notice that "The" and "the" are the same, the "." has been removed from the end of "ran", and "dog1" differs from "dog" and "dog2".


### UI

The program uses a command line interface to allow the user to perform the following actions. Note that the program continues to accept and process commands from the user until the user chooses to exit.

| Command | Functionality |
| ------ | -------- |  
| `find <asin>` | Given the ASIN number of a specific product, display a list of all reviews for that product and display a list of all questions and answers about that product. |  
| `reviewsearch <term>` | Given a one-word term, display a list of all reviews that contain the exact term. The results are sorted as described in Searching below. |  
| `qasearch <term>` | Given a one-word term, list of all question/answer documents that have the exact term in the question or answer. The results are sorted as described in Searching below. |  
| `reviewpartialsearch <term>` | Given a one-word term, display a list of all reviews where any word in the review contains a partial match to the term. A partial match is defined as the sequence of characters of the term appearing anywhere in the word, for example `he` would match `he`, `help`, and `then`. |  
| `qapartialsearch <term>` | Given a one-word term, display a list of question/answer documents where any word in the question or answer contains a partial match to the term. A partial match is defined as the sequence of characters of the term appearing anywhere in the word, for example `he` would match `he`, `help`, and `then`. |  

### Searching

For the exact search function (`reviewsearch <term>` or `qasearch <term>` command), results are sorted by [term frequency](https://nlp.stanford.edu/IR-book/html/htmledition/term-frequency-and-weighting-1.html). The result that appears first will be the result where the term appears most often in the document. In the example above, a search for "the" would return document 1 first, followed by document 2. This is because the term appears twice in document 1 and only once in document 2.

### Program Execution

Your main class is required to have the name `cs601.project1.AmazonSearch`.

Your main class will accept the following command line arguments: `-reviews <review_file_name> -qa <qa_file_name>`

The program exits if the arguments are not provided exactly as specified.


`java -cp project.jar cs601.project1.AmazonSearch -reviews <review_file_name> -qa <qa_file_name>` 
