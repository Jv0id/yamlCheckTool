/**
 * Copyright (c) 2018-2020, Sylvain Baudoin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fs.rules;

import com.fs.LintProblem;
import org.yaml.snakeyaml.tokens.KeyToken;
import org.yaml.snakeyaml.tokens.Token;
import org.yaml.snakeyaml.tokens.ValueToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Use this rule to control the number of spaces before and after colons ({@code :}).
 * <p>Options:</p>
 * <ul>
 *     <li>{@code max-spaces-before} defines the maximal number of spaces allowed before colons (use {@code -1} to disable).</li>
 *     <li>{@code max-spaces-after} defines the maximal number of spaces allowed after colons (use {@code -1} to disable).</li>
 * </ul>
 *
 * <p>Examples:</p>
 * <p>With <code>colons: {max-spaces-before: 0, max-spaces-after: 1}</code>
 * the following code snippet would **PASS**:
 * <pre>
 *     object:
 *       - a
 *       - b
 *     key: value
 * </pre>
 *
 * <p>With <code>colons: {max-spaces-before: 1}</code>
 * the following code snippet would **PASS**:
 * <pre>
 *     object :
 *       - a
 *       - b
 * </pre>
 * the following code snippet would **FAIL**:
 * <pre>
 *     object  :
 *       - a
 *       - b
 * </pre>
 *
 * <p>With <code>brackets: {max-spaces-after: 2}</code>
 * the following code snippet would **PASS**:
 * <pre>
 *     first:  1
 *     second: 2
 *     third:  3
 * </pre>
 * the following code snippet would **FAIL**:
 * <pre>
 *     first: 1
 *     2nd:   2
 *     third: 3
 * </pre>
 */
public class Colons extends TokenRule {
    public static final String OPTION_MAX_SPACES_BEFORE = "max-spaces-before";
    public static final String OPTION_MAX_SPACES_AFTER  = "max-spaces-after";


    public Colons() {
        registerOption(OPTION_MAX_SPACES_BEFORE, 0);
        registerOption(OPTION_MAX_SPACES_AFTER, 1);
    }

    @Override
    public List<LintProblem> check(Map<Object, Object> conf, Token token, Token prev, Token next, Token nextnext, Map<String, Object> context) {
        List<LintProblem> problems = new ArrayList<>();

        if (token instanceof ValueToken) {
            LintProblem problem = spacesBefore(token, prev,
                    -1,
                    (int)conf.get(OPTION_MAX_SPACES_BEFORE),
                    null,
                    "冒号前有太多空格");
            if (problem != null) {
                problems.add(problem);
            }

            problem = spacesAfter(token, next,
                    -1,
                    (int)conf.get(OPTION_MAX_SPACES_AFTER),
                    null,
                    "冒号后有太多空格");
            if (problem != null) {
                problems.add(problem);
            }
        }

        if (token instanceof KeyToken && isExplicitKey(token)) {
            LintProblem problem = spacesAfter(token, next,
                    -1,
                    (int)conf.get(OPTION_MAX_SPACES_AFTER),
                    null,
                    "问号后面有太多空格");
            if (problem != null) {
                problems.add(problem);
            }
        }

        return problems;
    }
}
