package hearnadam

import kyo.*

object Day1 extends KyoApp:
  def parseLine(line: String): Maybe[(Int, Int)] =
    line.split("   ") match
      case Array(a, b) =>
        Maybe.fromOption(a.toIntOption.flatMap(i => b.toIntOption.map(j => (i, j))))
      case _ => Maybe.empty

  // Seq((1, 2), (3, 4)) -> (Seq(1, 3), Seq(2, 4))
  def columns[A, B](lines: Seq[(A, B)]): (Chunk[A], Chunk[B]) =
    lines
      .foldLeft((Chunk.empty[A], Chunk.empty[B])) { case ((a, b), (a1, b1)) =>
        (a.append(a1), b.append(b1))
      }

  inline def indexed[A](size: Int, z: A)(f: (Int, A) => A): A =
    def loop(i: Int, z: A): A =
      if i == size then z
      else loop(i + 1, f(i, z))
    loop(0, z)

  def elementWiseDifference(l: Chunk[Int], r: Chunk[Int]): Int =
    indexed(l.length, 0):
      case (i, acc) => acc + Math.abs(r(i) - l(i))

  def elementWiseFrequency(l: Chunk[Int], r: Chunk[Int]): Int =
    indexed(l.length, 0):
      case (i, acc) => acc + (r.count(_ == l(i)) * l(i))

  run:
    Path("src/main/resources/day1.txt").readLines.map: lines =>
      val parsed        = lines.flatMap(parseLine)
      val (left, right) = columns(parsed)
      val part1         = elementWiseDifference(Chunk.from(left.sorted), Chunk.from(right.sorted))
      val part2         = elementWiseFrequency(Chunk.from(left.sorted), Chunk.from(right.sorted))
      Console.println:
        s"""|Part 1: $part1
            |Part 2: $part2
            |""".stripMargin
