package org.hearnadam

import kyo.*

object Day1 extends KyoApp:
  def parseLine(line: String): Maybe[(Int, Int)] =
    line.split("   ") match
      case Array(a, b) =>
        Maybe.fromOption(a.toIntOption.flatMap(i => b.toIntOption.map(j => (i, j))))
      case _ => Maybe.empty

  extension [A, B](self: Seq[(A, B)])
    // Seq((1, 2), (3, 4)) -> (Seq(1, 3), Seq(2, 4))
    def columns: (Chunk[A], Chunk[B]) =
      self
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
      val (left, right) = lines.flatMap(parseLine).columns
      val (l, r)        = (left.sorted.toChunk, right.sorted.toChunk)
      val part1         = elementWiseDifference(l, r)
      val part2         = elementWiseFrequency(l, r)
      Console.println:
        s"""|Part 1: $part1
            |Part 2: $part2
            |""".stripMargin
