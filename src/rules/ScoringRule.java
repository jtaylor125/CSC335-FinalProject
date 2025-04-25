/*
 * File:	ScoringRule.java
 * Author:	Tristan Emma
 * AI Help:	ChatGPT
 * Purpose: defines the @ScoringRule tag to be executed during runtime
 * 			as part of the metaprogrammed Dynamic Rule Loader 
 */
package rules;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ScoringRule {
    RuleType type();
}
