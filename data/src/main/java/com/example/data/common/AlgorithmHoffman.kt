package com.example.data.common

import java.util.PriorityQueue

class AlgorithmHoffman {

    private fun buildHuffmanTree(text: String): HuffmanNode {
        val charFrequency = text.groupingBy { it }.eachCount()
        val priorityQueue = PriorityQueue<HuffmanNode>(compareBy { it.frequency })
        charFrequency.forEach { (char, freq) ->
            priorityQueue.add(HuffmanNode(char, freq))
        }

        while (priorityQueue.size > 1) {
            val left = priorityQueue.poll()
            val right = priorityQueue.poll()
            val parent = HuffmanNode(null, left.frequency + right.frequency, left, right)
            priorityQueue.add(parent)
        }
        return priorityQueue.poll()
    }

    private fun generateCodes(root: HuffmanNode, code: String = ""): Map<Char, String> {
        val codes = mutableMapOf<Char, String>()
        generateCodesHelper(root, code, codes)
        return codes
    }

    private fun generateCodesHelper(node: HuffmanNode, code: String, codes: MutableMap<Char, String>) {
        if (node.char != null) {
            codes[node.char] = code
        } else {
            node.left?.let { generateCodesHelper(it, "$code${0}", codes) }
            node.right?.let { generateCodesHelper(it, "$code${1}", codes) }
        }
    }

    fun compress(text: String): String {
        val root = buildHuffmanTree(text)
        val codes = generateCodes(root)
        val compressed = StringBuilder()
        text.forEach { char ->
            compressed.append(codes[char])
        }
        return compressed.toString()
    }

}