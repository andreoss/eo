/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2022 Objectionary.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.eolang;

/**
 * Bytes.
 *
 * Represents byte array of arbitrary size,
 * convertible to a numeric value.
 * Original size is preserved by and, or and xor.
 *
 * @since 1.0
 */
public interface Bytes extends Data<byte[]> {
    /**
     * NOT operation.
     * @return Bytes.
     */
    Bytes not();

    /**
     * AND operation.
     * @param other Bytes.
     * @return Bytes.
     */
    Bytes and(Bytes other);

    /**
     * OR operation.
     * @param other Bytes.
     * @return Bytes.
     */
    Bytes or(Bytes other);

    /**
     * XOR operation.
     * @param other Bytes.
     * @return Bytes.
     */
    Bytes xor(Bytes other);

    /**
     * Left shift.
     * @param size Size.
     * @return Bytes.
     */
    Bytes left(int size);

    /**
     * Right shift.
     * @param size Size.
     * @return Bytes.
     */
    Bytes right(int size);

    /**
     * Convert to number.
     * @param type Type.
     * @return Number.
     * @param <T> Numeric type.
     */
    <T extends Number> T asNumber(Class<T> type);
}
