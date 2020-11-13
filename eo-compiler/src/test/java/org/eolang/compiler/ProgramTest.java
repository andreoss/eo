/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2020 Yegor Bugayenko
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
package org.eolang.compiler;

import com.jcabi.matchers.XhtmlMatchers;
import java.io.ByteArrayOutputStream;
import org.cactoos.io.DeadOutput;
import org.cactoos.io.InputOf;
import org.cactoos.io.OutputTo;
import org.cactoos.io.ResourceOf;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link Program}.
 *
 * @since 0.1
 */
public final class ProgramTest {

    @Test
    public void compilesSimpleCode() throws Exception {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Program program = new Program(
            new ResourceOf("org/eolang/compiler/fibonacci.eo"),
            new OutputTo(baos)
        );
        program.compile();
        MatcherAssert.assertThat(
            XhtmlMatchers.xhtml(
                new String(baos.toByteArray())
            ),
            XhtmlMatchers.hasXPath("/program/objects/o[@name='fibo']")
        );
    }

    @Test
    public void failsOnBrokenSyntax() {
        Assertions.assertThrows(
            CompileException.class,
            () -> {
                final Program program = new Program(
                    new InputOf("this code is definitely wrong"),
                    new DeadOutput()
                );
                program.compile();
            }
        );
    }

}
