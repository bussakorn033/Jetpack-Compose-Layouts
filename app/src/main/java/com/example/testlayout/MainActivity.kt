package com.example.testlayout

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import com.example.testlayout.ui.theme.TestLayoutTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.background
                ) {
                    /* List Layouts */
//                    PlayListLayoutPreview()
                    /* List Layouts */

                    /* Padding Layouts */
//                    TextWithPaddingToBaselinePreview()
//                    TextWithNormalPaddingPreview()

//                    CustomLayoutPreview()
//                    ChipLayoutPreview()
                    /* Padding Layouts */

                    /* Constraint Layouts */
//                    ConstraintLayoutContentPreview()
//                    LargeConstraintLayoutPreview()
                    DecoupledConstraintLayoutPreview()
                    /* Constraint Layouts */

//                    TwoTextsPreview()
                }
            }
        }
    }
}


/* ----------------------- TopAppBar ----------------------- */
@Composable
fun TopAppBar(title: String) {
    val context = LocalContext.current
    TopAppBar(
        backgroundColor = Color.Black,
        contentColor = Color(0xFFFFC0CB),
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        actions = {
            IconButton(onClick = {
                Toast.makeText(
                    context,
                    "click Favorite",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }) {
                Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
            }

            IconButton(onClick = {
                Toast.makeText(
                    context,
                    "click Search",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }

            IconButton(onClick = {
                Toast.makeText(
                    context,
                    "click MoreVert",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "MoreVert")
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                Toast.makeText(
                    context,
                    "click Menu",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }
    )

}
/* ----------------------- TopAppBar ----------------------- */

/* ----------------------- BottomAppBar ----------------------- */
@Composable
fun BottomAppBar() {
    val context = LocalContext.current
    BottomAppBar(
        backgroundColor = Color.Black,
        contentColor = Color(0xFFFFC0CB)
    )
    {
        IconButton(onClick = {
            Toast.makeText(
                context,
                "click Favorite",
                Toast.LENGTH_SHORT
            )
                .show()
        }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
        }
    }

}
/* ----------------------- BottomAppBar ----------------------- */


/* ----------------------- XXXXXXX ----------------------- */
@Composable
fun BarLayout(modifier: Modifier = Modifier /*child: () -> Any?*/) {

    Scaffold(
        topBar = { TopAppBar(title = "BarLayout") },
        bottomBar = { BottomAppBar() },
    ) { innerPadding ->
        Box(modifier.padding(innerPadding)) {}
//        child(modifier.padding(innerPadding))
//        { Childen }
    }
}
/* ----------------------- AppBar ----------------------- */


/* ----------------------- List ----------------------- */

@Composable
fun ButtonCard(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        enabled = if (1 == 1) true else false,
        colors = buttonColors(
            backgroundColor = Color(0xFFFFC0CB),
            contentColor = Color.Black,
            disabledBackgroundColor = Color.LightGray,
            disabledContentColor = Color.White
        ),
        shape = RoundedCornerShape(100.dp),
        onClick = {
            Toast.makeText(
                context,
                "click button",
                Toast.LENGTH_SHORT
            )
                .show()
        },
    ) {
        Row {
            Icon(
                Icons.Outlined.Favorite,
                contentDescription = "Favorite",
                modifier = modifier
                    .size(25.dp)
                    .fillMaxHeight()
                    .clip(MaterialTheme.shapes.small)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = modifier.width(4.dp))
            Text(
                text = "Button",
                modifier = modifier.align(Alignment.CenterVertically),
            )
        }
    }
}

@Composable
fun ButtonScroll(
    modifier: Modifier = Modifier,
    listSize: Int,
    scrollState: LazyListState,
    coroutineScope: CoroutineScope
) {
    val context = LocalContext.current
    Row(modifier = modifier.padding(8.dp)) {
        Button(
            modifier = modifier.fillMaxWidth(0.5f),
            colors = buttonColors(
                backgroundColor = Color(0xFFFFC0CB),
                contentColor = Color.Black,
                disabledBackgroundColor = Color.LightGray,
                disabledContentColor = Color.White
            ),
            onClick = {
                Toast.makeText(
                    context,
                    "click scroll to top",
                    Toast.LENGTH_SHORT
                )
                    .show()
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }) {
            Text(
                "Scroll to the top",
                maxLines = 1,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier.width(8.dp))
        Button(
            modifier = modifier.fillMaxWidth(),
            colors = buttonColors(
                backgroundColor = Color(0xFFFFC0CB),
                contentColor = Color.Black,
                disabledBackgroundColor = Color.LightGray,
                disabledContentColor = Color.White
            ),
            onClick = {
                Toast.makeText(
                    context,
                    "click scroll to end",
                    Toast.LENGTH_SHORT
                )
                    .show()
                coroutineScope.launch {
                    scrollState.animateScrollToItem(listSize - 1)
                }
            },
        ) {
            Text(
                "Scroll to the end",
                maxLines = 1,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier, index: Int) {
    val noIndex = index + 1
    val context = LocalContext.current
    Row(modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 8.dp)
        .height(IntrinsicSize.Min)
//        .border(width = 1.dp, color = Color.Black)
        .clip(RoundedCornerShape(5.dp))
        .background(MaterialTheme.colors.surface)
        .clickable(
            onClick = {
                Toast
                    .makeText(
                        context,
                        "click card $noIndex",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(8.dp)
        ) {
            Text(
                modifier = modifier.align(Alignment.CenterVertically),
                text = "$noIndex.",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = modifier.width(8.dp))
            Surface(
                modifier = modifier.size(50.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f)
            ) {
                // Image goes here
                Image(
                    contentScale = ContentScale.FillWidth,
                    painter = rememberImagePainter(data = "https://developer.android.com/images/brand/Android_Robot.png"),
                    contentDescription = "Android Logo",
                    modifier = modifier.size(50.dp)
                )
            }
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    "Alfred Sisley",
                    fontWeight = FontWeight.Bold
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        "3 minutes ago",
                        style = MaterialTheme.typography.body2
                    )
                }
            }
            ButtonCard()
        }
    }
}

@Composable
fun SimpleList(modifier: Modifier = Modifier) {
    val listSize = 40
//    val scrollState = rememberScrollState()
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier
            .fillMaxWidth()
            .background(Color.White)
//            .verticalScroll(scrollState)
    ) {
        ButtonScroll(modifier = Modifier, listSize, scrollState, coroutineScope)
//        repeat(listSize) {
        LazyColumn(state = scrollState) {
            items(listSize) {
                PhotographerCard(index = it)
            }
        }
    }
}

@Composable
fun ImageListItem(modifier: Modifier = Modifier, index: Int) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = "https://developer.android.com/images/brand/Android_Robot.png"),
            contentDescription = "Android Logo",
            modifier = modifier.size(50.dp)
        )
        Spacer(modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ImageList(modifier: Modifier = Modifier) {
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(20) {
            ImageListItem(modifier, it)
        }
    }
}

@Preview(
    showBackground = true,
//    showSystemUi = true,
)
@Composable
fun PlayListLayoutPreview(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = { TopAppBar(title = "PlayList Layout Preview") },
        bottomBar = { BottomAppBar() },
    ) { innerPadding ->
//        SimpleList()
        SimpleList(modifier.padding(innerPadding))
//        ImaxgeList(modifier.padding(innerPadding))
//        PhotographerCard(modifier.padding(innerPadding))
    }
}

/* ----------------------- List ----------------------- */


/* ----------------------- Layout ----------------------- */

@Composable
fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)

        // Check the composable has a first baseline
        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline]

        // Height of the composable with padding - first baseline
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            // Where the composable gets placed
            placeable.placeRelative(0, placeableY)
        }
    }
)

@Preview(
    showBackground = true,
//    showSystemUi = true,
)
@Composable
fun TextWithPaddingToBaselinePreview(modifier: Modifier = Modifier) {
    TestLayoutTheme() {
        Scaffold(
            topBar = { TopAppBar(title = "Text With Padding To Baseline Preview") },
            bottomBar = { BottomAppBar() },
        ) { innerPadding ->
            Text(
                text = "Text With Padding To Base line Preview\nHi there!",
                modifier
                    .firstBaselineToTop(32.dp)
                    .padding(innerPadding)
            )
        }
    }
}

@Preview(
    showBackground = true,
//    showSystemUi = true,
)
@Composable
fun TextWithNormalPaddingPreview(modifier: Modifier = Modifier) {
    TestLayoutTheme {
        Scaffold(
            topBar = { TopAppBar(title = "Text With Normal Padding Preview") },
            bottomBar = { BottomAppBar() },
        ) { innerPadding ->
            Box(modifier.padding(innerPadding)) {
                Text(
                    "Text With Normal Padding Preview\nHi there!",
                    modifier.padding(top = 32.dp)
                )
            }
        }
    }
}

/*-----------------------*/

@Composable
fun CustomLayout(
    modifier: Modifier = Modifier,
    // custom layout attributes
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // measure and position children given constraints logic here
        layout(constraints.maxWidth, constraints.maxHeight) {

        }
    }
}

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.map { measurable ->
            // Measure each child
            measurable.measure(constraints)
        }

        // Track the y co-ord we have placed children up to
        var yPosition = 0

        // Set the size of the layout as big as it can
        layout(constraints.maxWidth, constraints.maxHeight) {
            // Place children in the parent layout
            placeables.forEach { placeable ->
                // Position item on the screen
                placeable.placeRelative(x = 0, y = yPosition)

                // Record the y co-ord placed up to
                yPosition += placeable.height
            }
        }
    }
}

@Preview(
    showBackground = true,
//    showSystemUi = true,
)
@Composable
fun CustomLayoutPreview(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { TopAppBar(title = "Custom Layout Preview") },
        bottomBar = { BottomAppBar() },
    ) { innerPadding ->
        Box(modifier.padding(innerPadding)) {
            MyOwnColumn(modifier.padding(top = 26.dp)) {
                Text("MyOwnColumn")
                Text("places items")
                Text("vertically.")
                Text("We've done it by hand!")
            }
        }
    }
}

/*-----------------------*/

@Composable
fun StaggeredGrid(
    modifier: Modifier = Modifier,
    rows: Int = 3,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->

        // Keep track of the width of each row
        val rowWidths = IntArray(rows) { 0 }

        // Keep track of the max height of each row
        val rowHeights = IntArray(rows) { 0 }

        // Don't constrain child views further, measure them with given constraints
        // List of measured children
        val placeables = measurables.mapIndexed { index, measurable ->

            // Measure each child
            val placeable = measurable.measure(constraints)

            // Track the width and max height of each row
            val row = index % rows
            rowWidths[row] += placeable.width
            rowHeights[row] = Math.max(rowHeights[row], placeable.height)

            placeable
        }

        // Grid's width is the widest row
        val width = rowWidths.maxOrNull()
            ?.coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth)) ?: constraints.minWidth

        // Grid's height is the sum of the tallest element of each row
        // coerced to the height constraints
        val height = rowHeights.sumOf { it }
            .coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))

        // Y of each row, based on the height accumulation of previous rows
        val rowY = IntArray(rows) { 0 }
        for (i in 1 until rows) {
            rowY[i] = rowY[i - 1] + rowHeights[i - 1]
        }

        // Set the size of the parent layout
        layout(width, height) {
            // x cord we have placed up to, per row
            val rowX = IntArray(rows) { 0 }

            placeables.forEachIndexed { index, placeable ->
                val row = index % rows
                placeable.placeRelative(
                    x = rowX[row],
                    y = rowY[row]
                )
                rowX[row] += placeable.width
            }
        }

    }
}

@Composable
fun ChipItem(modifier: Modifier = Modifier, text: String, idx: Number) {
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp, 16.dp)
                    .background(color = MaterialTheme.colors.secondary)
            )
            Spacer(Modifier.width(4.dp))
            Text(text = "$idx. $text")
        }
    }
}

val topics = listOf(
    "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
    "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
    "Religion", "Social sciences", "Technology", "TV", "Writing"
)

//@Composable
//fun ChipList(modifier: Modifier = Modifier) {
//    Row(modifier = modifier.horizontalScroll(rememberScrollState())) {
//        StaggeredGrid(modifier = modifier, rows = 5) {
//            for ((index, topic) in topics.withIndex()) {
//                ChipItem(modifier = Modifier.padding(8.dp), idx = index+1, text = topic)
//            }
//        }
//    }
//}

@Composable
fun ChipList(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(color = Color.LightGray, shape = RectangleShape)
            .size(200.dp)
            .padding(16.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        StaggeredGrid(modifier = modifier, rows = 3) {
            for ((index, topic) in topics.withIndex()) {
                ChipItem(modifier = Modifier.padding(8.dp), idx = index + 1, text = topic)
            }
        }
    }
}

@Preview(
    showBackground = true,
//    showSystemUi = true,
)
@Composable
fun ChipLayoutPreview(modifier: Modifier = Modifier) {
    TestLayoutTheme {
        Scaffold(
            topBar = { TopAppBar(title = "Chip Layout Preview") },
            bottomBar = { BottomAppBar() },
        ) { innerPadding ->
            ChipList(modifier.padding(innerPadding))
        }
    }
}

// How to create a modifier
@Stable
fun Modifier.padding(all: Dp) =
    this.then(
        PaddingModifier(
            start = all,
            top = all,
            end = all,
            bottom = all,
            rtlAware = true
        )
    )

// Implementation detail
private class PaddingModifier(
    val start: Dp = 0.dp,
    val top: Dp = 0.dp,
    val end: Dp = 0.dp,
    val bottom: Dp = 0.dp,
    val rtlAware: Boolean,
) : LayoutModifier {

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {

        val horizontal = start.roundToPx() + end.roundToPx()
        val vertical = top.roundToPx() + bottom.roundToPx()

        val placeable = measurable.measure(constraints.offset(-horizontal, -vertical))

        val width = constraints.constrainWidth(placeable.width + horizontal)
        val height = constraints.constrainHeight(placeable.height + vertical)
        return layout(width, height) {
            if (rtlAware) {
                placeable.placeRelative(start.roundToPx(), top.roundToPx())
            } else {
                placeable.place(start.roundToPx(), top.roundToPx())
            }
        }
    }
}

/*-----------------------*/


/* ----------------------- Layout ----------------------- */


/* ----------------------- Layout modifiers under the hood ----------------------- */

//
//val topics = listOf(
//    "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
//    "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
//    "Religion", "Social sciences", "Technology", "TV", "Writing"
//)

@Composable
fun LayoutsCodelab() {
    Scaffold(
        topBar = { TopAppBar(title = "Layouts Code lab") }
    ) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .background(color = Color.LightGray)
        .padding(16.dp)
        .size(200.dp)
        .horizontalScroll(rememberScrollState()),
        content = {
            StaggeredGrid {
                for (topic in topics) {
                    Chip(modifier = Modifier.padding(8.dp), text = topic)
                }
            }
        })
}

//@Composable
//fun StaggeredGrid(
//    modifier: Modifier = Modifier,
//    rows: Int = 3,
//    content: @Composable () -> Unit
//) {
//    Layout(
//        modifier = modifier,
//        content = content
//    ) { measurables, constraints ->
//
//        // Keep track of the width of each row
//        val rowWidths = IntArray(rows) { 0 }
//
//        // Keep track of the max height of each row
//        val rowHeights = IntArray(rows) { 0 }
//
//        // Don't constrain child views further, measure them with given constraints
//        // List of measured children
//        val placeables = measurables.mapIndexed { index, measurable ->
//            // Measure each child
//            val placeable = measurable.measure(constraints)
//
//            // Track the width and max height of each row
//            val row = index % rows
//            rowWidths[row] += placeable.width
//            rowHeights[row] = Math.max(rowHeights[row], placeable.height)
//
//            placeable
//        }
//
//        // Grid's width is the widest row
//        val width = rowWidths.maxOrNull()
//            ?.coerceIn(constraints.minWidth.rangeTo(constraints.maxWidth)) ?: constraints.minWidth
//
//        // Grid's height is the sum of the tallest element of each row
//        // coerced to the height constraints
//        val height = rowHeights.sumOf { it }
//            .coerceIn(constraints.minHeight.rangeTo(constraints.maxHeight))
//
//        // Y of each row, based on the height accumulation of previous rows
//        val rowY = IntArray(rows) { 0 }
//        for (i in 1 until rows) {
//            rowY[i] = rowY[i - 1] + rowHeights[i - 1]
//        }
//
//        // Set the size of the parent layout
//        layout(width, height) {
//            // x co-ord we have placed up to, per row
//            val rowX = IntArray(rows) { 0 }
//
//            placeables.forEachIndexed { index, placeable ->
//                val row = index % rows
//                placeable.placeRelative(
//                    x = rowX[row],
//                    y = rowY[row]
//                )
//                rowX[row] += placeable.width
//            }
//        }
//    }
//}

@Composable
fun Chip(modifier: Modifier = Modifier, text: String) {
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp, 16.dp)
                    .background(color = MaterialTheme.colors.secondary)
            )
            Spacer(Modifier.width(4.dp))
            Text(text = text)
        }
    }
}

@Preview(
    showBackground = true,
//    showSystemUi = true,
)
@Composable
fun ChipPreview() {
    TestLayoutTheme {
        Chip(text = "Hi there")
    }
}

@Preview(
    showBackground = true,
//    showSystemUi = true,
)
@Composable
fun LayoutsCodelabPreview() {
    TestLayoutTheme {
        LayoutsCodelab()
    }
}


/* ----------------------- Layout modifiers under the hood ----------------------- */



/* ----------------------- Constraint Layout Content ----------------------- */
@Composable
fun ConstraintLayoutContent(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = Modifier
//            .fillMaxWidth()
            .background(Color(0xFFFFC0CB))
    ) {

//        // Create references for the composables to constrain
//        val (button, text) = createRefs()
//
//        Button(
//            onClick = { /* Do something */ },
//            // Assign reference "button" to the Button composable
//            // and constrain it to the top of the ConstraintLayout
//            modifier = Modifier.constrainAs(button) {
//                centerHorizontallyTo(parent)
////                centerVerticallyTo(parent)
////                top.linkTo(parent.top, margin = 50.dp)
////                bottom.linkTo(parent.bottom, margin = 50.dp)
//            }
//        ) {
//            Text("Button")
//        }
//
//        // Assign reference "text" to the Text composable
//        // and constrain it to the bottom of the Button composable
//        Text("Text",
//            Modifier.constrainAs(text) {
//                top.linkTo(button.bottom, margin = 40.dp)
//                centerHorizontallyTo(parent)
//            }
//        )

        /*-----------------------*/

        val (button1, button2, text1, text2) = createRefs()

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button 1")
        }

        Text("Text1", Modifier.constrainAs(text1) {
            top.linkTo(button1.bottom, margin = 16.dp)
            centerAround(button1.end)
        })

        val barrier = createEndBarrier(button1, text1)
        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text("Button 2")
        }

        Text("Text2", Modifier.constrainAs(text2) {
            top.linkTo(button2.top, margin = -17.dp)
            centerAround(button2.start)
        })

    }
}

/*-----------------------*/

@Composable
fun LargeConstraintLayout(modifier: Modifier = Modifier) {

    ConstraintLayout(
        modifier = Modifier
            .background(Color(0xFFFFC0CB))
    ) {
        val text = createRef()

        val guideline = createGuidelineFromStart(0.5f)
        Text(
            "This is a very very very very very very very long text",
            Modifier.constrainAs(text) {
                linkTo(guideline, parent.end)
                width = Dimension.preferredWrapContent
//                width = Dimension.preferredWrapContent.atLeast(50.dp)
//                width = Dimension.fillToConstraints
//                width = Dimension.preferredValue(10.dp)
//                width = Dimension.value(10.dp)
            }
        )
    }
}
/*-----------------------*/

@Composable
fun DecoupledConstraintLayout(modifier: Modifier = Modifier) {
    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(margin = 20.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 100.dp) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}


@Preview(
    showBackground = true,
//    showSystemUi = true,
)
@Composable
fun ConstraintLayoutContentPreview(modifier: Modifier = Modifier) {
    TestLayoutTheme {
        Scaffold(
            topBar = { TopAppBar(title = "Constraint Layout Content Preview") },
            bottomBar = { BottomAppBar() }
        ) { innerPadding ->
            ConstraintLayoutContent(modifier = modifier.padding(innerPadding))
        }
    }
}


@Preview(
    showBackground = true,
//    showSystemUi = true,
)
@Composable
fun LargeConstraintLayoutPreview(modifier: Modifier = Modifier) {
    TestLayoutTheme {
        Scaffold(
            topBar = { TopAppBar(title = "Large Constraint Layout Preview") },
            bottomBar = { BottomAppBar() }
        ) { innerPadding ->
            LargeConstraintLayout(modifier = modifier.padding(innerPadding))
        }
    }
}


@Preview(
    showBackground = true,
//    showSystemUi = true,
)
@Composable
fun DecoupledConstraintLayoutPreview(modifier: Modifier = Modifier) {
    TestLayoutTheme {
        Scaffold(
            topBar = { TopAppBar(title = "Decoupled Constraint Layout Preview") },
            bottomBar = { BottomAppBar() }
        ) { innerPadding ->
            DecoupledConstraintLayout(modifier = modifier.padding(innerPadding))
        }
    }
}
/* ----------------------- Constraint Layout Content ----------------------- */



/* -----------------------  Intrinsic ----------------------- */
@Composable
fun TwoTexts(modifier: Modifier = Modifier, text1: String, text2: String) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier
                .weight(1f)
                .background(Color.Blue)
                .padding(start = 16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally),
            text = text1,
            color = Color.White,
        )

        Divider(
            color = Color.Black, modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .background(Color.Gray)
                .padding(end = 16.dp)
                .wrapContentWidth(Alignment.End),
            text = text2,
            color = Color.White,
        )
    }
}

@Preview
@Composable
fun TwoTextsPreview() {
    TestLayoutTheme {
        Surface {
            TwoTexts(text1 = "Hi", text2 = "there")
        }
    }
}
/* ----------------------- Intrinsic ----------------------- */