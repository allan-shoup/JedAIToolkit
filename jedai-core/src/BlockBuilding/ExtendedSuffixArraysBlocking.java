/*
* Copyright [2016] [George Papadakis (gpapadis@yahoo.gr)]
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package BlockBuilding;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author gap2
 */
public class ExtendedSuffixArraysBlocking extends SuffixArraysBlocking {

    public ExtendedSuffixArraysBlocking(int maxSize, int minLength) {
        super(maxSize, minLength);
    }

    @Override
    protected Set<String> getBlockingKeys(String attributeValue) {
        final Set<String> suffixes = new HashSet<>();
        for (String token : getTokens(attributeValue)) {
            suffixes.addAll(getExtendedSuffixes(minimumSuffixLength, token));
        }
        return suffixes;
    }
    
    @Override
    public String getMethodInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMethodParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Set<String> getExtendedSuffixes(int minimumLength, String blockingKey) {
        final Set<String> suffixes = new HashSet<>();
        suffixes.add(blockingKey);
        if (minimumLength <= blockingKey.length()) {
            for (int nGramSize = blockingKey.length() - 1; minimumLength <= nGramSize; nGramSize--) {
                int currentPosition = 0;
                final int length = blockingKey.length() - (nGramSize - 1);
                while (currentPosition < length) {
                    String newSuffix = blockingKey.substring(currentPosition, currentPosition + nGramSize);
                    suffixes.add(newSuffix);
                    currentPosition++;
                }
            }
        }
        return suffixes;
    }
}