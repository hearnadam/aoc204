package org.hearnadam

import kyo.Chunk

extension [A](self: Seq[A]) inline def toChunk: Chunk[A] = Chunk.from(self)
