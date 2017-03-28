package objsets

/**
 * Created by mdaviot on 6/13/16.
 */
object Main extends App {
  // Some help printing the results:
  println("RANKED:")
  GoogleVsApple.trending foreach println
}

object GoogleVsApple {
  val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")

  val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

  def contient(liste: List[String], texte: String): Boolean = {
    liste.filter((mot: String) => texte.contains(mot)).length != 0
  }

  val googleTweets: TweetSet =
    TweetReader.allTweets.filter((tweet: Tweet) => contient(google, tweet.text))

  val appleTweets: TweetSet =
    TweetReader.allTweets.filter((tweet: Tweet) => contient(apple, tweet.text))

  val trending: Trending =
    googleTweets.union(appleTweets).ascendingByRetweet
  //TODO what is the tweet with highest #retweets?
}
