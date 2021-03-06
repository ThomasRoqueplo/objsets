package objsets

class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet {

  // The following methods are provided for you, and do not have to be changed
  // -------------------------------------------------------------------------
  def contains(x: Tweet): Boolean =
    if (x.text < elem.text) left.contains(x)
    else if (elem.text < x.text) right.contains(x)
    else true

  // returns a new TweetSet which includes x
  def incl(x: Tweet): TweetSet = {
    if (x.text < elem.text) new NonEmpty(elem, left.incl(x), right)
    else if (elem.text < x.text) new NonEmpty(elem, left, right.incl(x))
    else this
  }

  def isEmpty = false

  def head = if (left.isEmpty) elem else left.head

  def tail = if (left.isEmpty) right else new NonEmpty(elem, left.tail, right)

  def remove(tw: Tweet): TweetSet =
    if (tw.text < elem.text) new NonEmpty(elem, left.remove(tw), right)
    else if (elem.text < tw.text) new NonEmpty(elem, left, right.remove(tw))
    else left.union(right)

  // -------------------------------------------------------------------------

  def filter0(p: Tweet => Boolean, acc: TweetSet): TweetSet = {
    def includeOuNon(p: Tweet => Boolean, acc: TweetSet): TweetSet =
      if (p(elem))
        acc.incl(elem)
      else
        acc

    includeOuNon(p, left.filter0(p, right.filter0(p, acc)))
  }

  override def union(that: TweetSet): TweetSet =
    left.union(right.union(that.incl(elem)))

}
