package com.example.base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

abstract class BaseMviFragment<State : Any, Message : Any, Dependency : Any> : Fragment {

  private val TAG = this::class.simpleName

  protected abstract val viewModel: BaseMviViewModel<State, Message, Dependency>

  constructor() : super()
  constructor(@LayoutRes layoutRes: Int) : super(layoutRes)

  @CallSuper
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initBackButtonHandler()
    initDispatchers()

    lifecycleScope.launchWhenResumed {
      viewModel.state.collect { state ->
        renderAndLog(state)
      }
    }

    viewModel.onCreated()
  }

  protected open val backButtonCallback: (() -> Unit)? = null

  abstract fun initDispatchers()
  abstract fun render(state: State)

  protected fun dispatch(Message: Message) {
    viewModel.dispatch(Message)
  }

  private fun initBackButtonHandler() {
    val currentCallback = backButtonCallback
    if (currentCallback != null) {
      val backCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
          currentCallback.invoke()
        }
      }
      requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, backCallback)
    }
  }

  private fun renderAndLog(state: State) {
    Log.v(TAG,"Rendering state: $state")
    render(state)
  }
}
